import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { FormService } from '../../services/form-service';
import { Country } from '../../commons/country';
import { State } from '../../commons/state';
import { CustomValidators } from '../../commons/custom-validators';
import { CartService } from '../../services/cart.service';
import { CheckoutService } from '../../services/checkout.service';
import { Router } from '@angular/router';
import { Order } from '../../commons/order';
import { OrderItem } from '../../commons/order-item';
import { Purchase } from '../../commons/purchase';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrl: './checkout.component.css'
})
export class CheckoutComponent implements OnInit{


  countries: Country[];

  checkOutFormGroup!: FormGroup;

  shippingAddressStates: State[] = [];

  billingAddressStates: State[] = [];

  totalPrice: number = 0;
  totalQuantity: number = 0;

  creditCardMonths: number[] = [];
  creditCardYears: number[] = [];


  constructor(private formBuilder: FormBuilder,
    private formService: FormService,
    private cartService: CartService,
    private checkoutService: CheckoutService,
    private router: Router
  ){

  }

  ngOnInit(): void {

      this.reviewCartDetails();

      this.checkOutFormGroup = 
      this.formBuilder.group({
        customer: this.formBuilder.group({
          firstName: new FormControl('', [Validators.required, Validators.minLength(2), CustomValidators.notOnlyWhitespace]),
          lastName: new FormControl('', [Validators.required, Validators.minLength(2), CustomValidators.notOnlyWhitespace]),
          email: new FormControl('', 
            [Validators.required,
              Validators.pattern('^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$')
            ]
          )
        }),
        shippingAddress: this.formBuilder.group({
          street: new FormControl('', [Validators.required, Validators.minLength(2), CustomValidators.notOnlyWhitespace]),
          city: new FormControl('', [Validators.required, Validators.minLength(2), CustomValidators.notOnlyWhitespace]),
          state: new FormControl('', [Validators.required]),
          country: new FormControl('', [Validators.required]),
          zipCode: new FormControl('', [Validators.required, Validators.minLength(2), CustomValidators.notOnlyWhitespace])
        }),
        billingAddress: this.formBuilder.group({
          street: new FormControl('', [Validators.required, Validators.minLength(2), CustomValidators.notOnlyWhitespace]),
          city: new FormControl('', [Validators.required, Validators.minLength(2), CustomValidators.notOnlyWhitespace]),
          state: new FormControl('', [Validators.required]),
          country: new FormControl('', [Validators.required]),
          zipCode: new FormControl('', [Validators.required, Validators.minLength(2), CustomValidators.notOnlyWhitespace])
        }),
        creditCard: this.formBuilder.group({
          cardType: new FormControl('', [Validators.required]),
          nameOfCard: new FormControl('', [Validators.required, Validators.minLength(2), CustomValidators.notOnlyWhitespace]),
          cardNumber: new FormControl('', [Validators.required, Validators.pattern('[0-9]{16}')]),
          securityCode: new FormControl('', [Validators.required, Validators.pattern('[0-9]{3}')]),
          expirationMonth: [''],
          expirationYear: ['']
        })
      });

      const startMonth: number = new Date().getMonth() + 1;
      console.log("startMonth: " + startMonth);
      this.formService.getCreditCardMonths(startMonth)
      .subscribe(data => {
        console.log("Retrieve credit card months: " + JSON.stringify(data));
        this.creditCardMonths = data;
      })

      this.formService.getCreditCardYears()
      .subscribe(data => {
        console.log("Retrieve credit card years: " + JSON.stringify(data));
        this.creditCardYears = data;
      })

      this.formService.getCountries()
      .subscribe(data => {
        console.log("Retrieved countries: " + JSON.stringify(data));
        this.countries = data;
      })

  }

  reviewCartDetails(){
      this.cartService.totalPrice.subscribe(totalPrice => {
        this.totalPrice = totalPrice;
      })

      this.cartService.totalQuantity.subscribe(totalQuantity => {
          this.totalQuantity = totalQuantity;
      
      })
  }

  onSubmit(){



    console.log("handling the submit button");

    if(this.checkOutFormGroup.invalid){
      this.checkOutFormGroup.markAllAsTouched();
      return;
    }

    let order = new Order();
    order.totalPrice = this.totalPrice;
    order.totalQuantity = this.totalQuantity;

    const cartItems = this.cartService.cartItem;

    // let orderItems: OrderItem[] = [];
    // for(let i = 0; i < cartItems.length; i++){
    //   orderItems[i] = new OrderItem(cartItems[i]);
    // }

    let orderItems: OrderItem[] = cartItems.map(tempCartItem => new OrderItem(tempCartItem));


    let purchase = new Purchase();

    purchase.customer = this.checkOutFormGroup.controls['customer'].value;

    
    purchase.shippingAddress = this.checkOutFormGroup.controls['shippingAddress'].value;
    const shippingState: State = JSON.parse(JSON.stringify(purchase.shippingAddress.state));
    const shippingCountry: Country = JSON.parse(JSON.stringify(purchase.shippingAddress.country));

    purchase.shippingAddress.state = shippingState.name;
    purchase.shippingAddress.country = shippingCountry.name;

    purchase.billingAddress = this.checkOutFormGroup.controls['billingAddress'].value;
    const billingState: State = JSON.parse(JSON.stringify(purchase.billingAddress.state));
    const billingCountry: Country = JSON.parse(JSON.stringify(purchase.billingAddress.country));

    purchase.billingAddress.state = billingState.name;
    purchase.billingAddress.country = billingCountry.name;


    purchase.order = order;
    purchase.orderItems = orderItems;

    this.checkoutService.placeOrder(purchase).subscribe(
      {
        next: response => {
          alert(`Your order has been received.\n Order tracking number: ${response.orderTrackingNumber}`);
          this.resetCart();
        },
        error: err => {
          alert(`There was an error: ${err.message}`);
        }
      }
    )

  }

  resetCart() {
    this.cartService.cartItem = [];
    this.cartService.totalPrice.next(0);
    this.cartService.totalQuantity.next(0);

    this.checkOutFormGroup.reset();

    this.router.navigateByUrl("/product");

  }

  get firstName(){
    return this.checkOutFormGroup.get('customer.firstName');
  }

  get lastName(){
    return this.checkOutFormGroup.get('customer.lastName');
  }

  get email(){
    return this.checkOutFormGroup.get('customer.email');
  }

  get shippingAddressStreet(){
    return this.checkOutFormGroup.get('shippingAddress.street');
  }

  get shippingAddressCity(){
    return this.checkOutFormGroup.get('shippingAddress.city');
  }

  get shippingAddressState(){
    return this.checkOutFormGroup.get('shippingAddress.state');
  }

  get shippingAddressCountry(){
    return this.checkOutFormGroup.get('shippingAddress.country');
  }

  get shippingAddressZipCode(){
    return this.checkOutFormGroup.get('shippingAddress.zipCode');
  }

  get billingAddressStreet(){
    return this.checkOutFormGroup.get('billingAddress.street');
  }

  get billingAddressCity(){
    return this.checkOutFormGroup.get('billingAddress.city');
  }

  get billingAddressState(){
    return this.checkOutFormGroup.get('billingAddress.state');
  }

  get billingAddressCountry(){
    return this.checkOutFormGroup.get('billingAddress.country');
  }

  get billingAddressZipCode(){
    return this.checkOutFormGroup.get('billingAddress.zipCode');
  }

  get creditCardType(){
    return this.checkOutFormGroup.get('creditCard.cardType');
  }

  get creditCardNameOfCard(){
    return this.checkOutFormGroup.get('creditCard.nameOfCard');
  }

  get creditCardNumber(){
    return this.checkOutFormGroup.get('creditCard.cardNumber');
  }

  get creditCardSecurityCode(){
    return this.checkOutFormGroup.get('creditCard.securityCode');
  }

  copyShippingAddressToBillingAddress(event: any) {
    if(event.target.checked){
      this.checkOutFormGroup.controls['billingAddress']
      .setValue(this.checkOutFormGroup.controls['shippingAddress'].value);

      this.billingAddressStates = this.shippingAddressStates;
    }
    else{
      this.checkOutFormGroup.controls['billingAddress'].reset();
      this.billingAddressStates = [];
    }
  }

  handleMonthsAndYears() {
    const creditCardFormGroup = this.checkOutFormGroup.get("creditCard");

    const currentYear: number = new Date().getFullYear();

    const selectedYear: number = Number(creditCardFormGroup.value.expirationYear);

    let startMonth: number;

    if(currentYear == selectedYear){
      startMonth = new Date().getMonth() + 1;
    }
    else{
      startMonth = 1;
    }

    this.formService.getCreditCardMonths(startMonth)
    .subscribe(data => {
      console.log("Retrieved credit card months: " + JSON.stringify(data));
      this.creditCardMonths = data;
    })
  }

  getStates(formGroupName: string){
    const formGroup = this.checkOutFormGroup.get(formGroupName);
    const countryCode = formGroup.value.country.code;

    this.formService.getStates(countryCode)
    .subscribe(data => {
        if(formGroupName === 'shippingAddress'){
          this.shippingAddressStates = data;
        }
        else{
          this.billingAddressStates = data;
        }
        formGroup.get('state').setValue(data[0]);
    })

   
  }

}
