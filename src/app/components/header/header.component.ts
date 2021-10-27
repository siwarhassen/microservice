import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  registerForm?: FormGroup;
  email?: string ;
  password?: string;
  error?: string;
  constructor(private router: Router,
    private authenticationService: AuthService) { }

  ngOnInit(): void {

    this.registerForm =  new FormGroup({
      Firstname: new FormControl('', [Validators.required, Validators.minLength(3)]),
      Lastname: new FormControl('', [Validators.required, Validators.minLength(3)]),
      Email: new FormControl('', [ Validators.required, Validators.pattern('^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$')]),
     
      Password: new FormControl('', [Validators.required, Validators.minLength(4) ,	Validators.pattern('^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])[a-zA-Z0-9]+$')]),
   
    }
   
    
    );
  }


  login(e:any) {

    e.preventDefault();
 
 console.log(this.email)
   /*  this.authenticationService.login(this.email!, this.password!)
       .subscribe(result => {
 
         console.log(result);
         this.router.navigate(['/articles']);
 
       }, loginError => this.error = loginError.message + ' : verify  your username or password !  ');
 
 
   }*/

}


submit()
{
console.log(this.registerForm?.value)
 /* this.authenticationService.register(this.registerForm?.value).pipe(
  map(u=>console.log("success"))
 
).subscribe( ( u: any ) => this.router.navigate(['login']) )*/
}

}
