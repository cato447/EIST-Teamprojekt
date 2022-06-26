<template>

  <div class="main">

    <div v-if="loading">
      <h1 class="loading">Loading...</h1>
    </div>
    <div v-else>

      <!-- navbar -->

      <header class="navbar-header">
        <div class="logo">
          <a>Login</a>
        </div>

        <input type="checkbox" class="menu-btn" id="menu-btn">
        <label for="menu-btn" class="menu-icon">
          <span class="menu-icon__line"></span>
        </label>

        <ul class="nav-links">
          <li class="nav-link">
            <a href="#">Profile</a>
          </li>
          <li class="nav-link">
            <a href="#">Storage</a>
          </li>
          <li class="nav-link">
            <a href="#">Recipes</a>
          </li>
          <li class="nav-link">
            <a href="#">About</a>
          </li>
        </ul>
      </header>

      <section>
        <div class="container">

          <div class="user-login" id="user-sign-in">
            <div class="site-background"/>
            <div class="form-background">
              <form>

                <h2>Sign In</h2>
                <input type="text" placeholder="Username">
                <input type="password" placeholder="Password">
                <input type="submit" class="login-btn-apple" value="Login Apple">
                <input type="submit" class="login-btn" value="Login">
                <p class="signup">Don't have an account?
                  <a href="#" v-on:click="isSignUp = !isSignUp">Sign up</a>
                </p>

              </form>
            </div>
          </div>

          <div class="user-signup" :class="{toggleOff:isSignUp}">
            <div class="site-background"/>
            <div class="form-background">
              <form>

                <h2>Create Account</h2>
                <input type="text" placeholder="Username">
                <input type="text" placeholder="Email Address">
                <input type="password" placeholder="Create Password">
                <input type="password" placeholder="Confirm Password">
                <input type="submit" value="Sign Up">
                <p class="signup">Already have an account
                  <br>
                  <a href="#" v-on:click="isSignUp = !isSignUp">Sign in</a>
                </p>

              </form>
            </div>
          </div>

        </div>
      </section>
      
    </div>
  </div>

</template>





<script>

export default {
  name: "LoginPage",
  data() {
    return {
      isSignUp: true
    }
  }
}


</script>





<style lang="scss">

@import url('https://fonts.googleapis.com/css2?family=Montserrat:wght@100;200&display=swap');
@import 'src/styling/navbar';

*,
*::before,
*::after {
  box-sizing: border-box;
  margin: 0;
  padding: 0;
}

.main {
  font-family: 'Montserrat', sans-serif;
  height: 100vh;
  display: grid;
  justify-content: center;
  align-items: center;
  font-size: 1.25rem;
  background: #213737;
}

section{
  position: relative;
  min-height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 40px;
  transition: 0.5s;

  .container{
    position: relative;
    width: 1200px;
    height: 750px;
    background: white;
    box-shadow: 0 15px 50px rgba(0, 0, 0, 0.6);
  }

  .user-login{
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    display: flex;

    .site-background{
      position: relative;
      width: 50%;
      height: 100%;
      transition: 0.5s;
      background: url('../ressouce/5008bf96009ea69ba157815061bce4f2.png');
    }

    .form-background{
      position: relative;
      width: 50%;
      height: 100%;
      background: white;
      display: flex;
      justify-content: center;
      align-items: center;
      padding: 80px;
    }

    h2{
      position: relative;
      font-size: 30px;
      font-weight: 600;
      text-transform: uppercase;
      letter-spacing: 4px;
      text-align: center;
      width: 100%;
      margin-bottom: 10px;
      color: black;
      top: -15px;
    }

    h2:after{
      content:'';
      display:block;
      border-bottom:2px solid #000;
      height:0;
      position:relative;
      top: 25px;

    }

    input{
      position: relative;
      width: 100%;
      padding: 20px;
      background: lightgrey;
      border: none;
      outline: none;
      box-shadow: none;
      font-size: 20px;
      letter-spacing: 2px;
      margin: 10px 0;
      top: 25px;
      color: black;
    }

    input[type="submit"]{
      cursor: pointer;
      max-width: 210px;
      transition: 0.5s;
      background-color: darkslategrey;
      color: white;
    }

    .login-btn{
      position: relative;
      left: 20px;
    }

    .signup{
      text-align: center;
      position: relative;
      margin-top: 300px;
      font-size: 20px;
      text-transform: uppercase;
      letter-spacing: 2px;
    }

    a{
      font-weight: 600;
      text-decoration: none;
      color: darkslategrey;
      letter-spacing: 2px;
    }
  }

  .user-signup{
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;

    .site-background{
      position: relative;
      width: 50%;
      height: 100%;
      left: 50%;
      transition: 0.5s;
      background: url('../ressouce/5008bf96009ea69ba157815061bce4f2.png');
    }

    .form-background{
      position: relative;
      width: 50%;
      height: 100%;
      background: white;
      display: flex;
      justify-content: center;
      align-items: center;
      padding: 80px;
      top: -100%;
    }

    h2{
      position: relative;
      font-size: 30px;
      font-weight: 600;
      text-transform: uppercase;
      letter-spacing: 4px;
      text-align: center;
      width: 100%;
      margin-bottom: 10px;
      color: black;
      top: 75px;
    }

    h2:after{
      content:'';
      display:block;
      border-bottom:2px solid #000;
      height:0;
      position:relative;
      top: 25px;

    }

    input{
      position: relative;
      width: 100%;
      padding: 20px;
      background: lightgrey;
      border: none;
      outline: none;
      box-shadow: none;
      font-size: 20px;
      letter-spacing: 2px;
      margin: 10px 0;
      top: 115px;
      color: black;
    }

    input[type="submit"]{
      cursor: pointer;
      max-width: 210px;
      transition: 0.5s;
      background-color: darkslategrey;
      color: white;
      left: 52%;
    }

    .signup{
      text-align: center;
      position: relative;
      margin-top: 280px;
      font-size: 20px;
      text-transform: uppercase;
      letter-spacing: 2px;
      top: -85px;
    }

    a{
      text-align: center;
      font-weight: 600;
      text-decoration: none;
      color: darkslategrey;
      letter-spacing: 2px;
    }
  }
}

.toggleOff{
  display: none;
}

@media (max-height: 1440px) {

  section{
    padding: 27px;

    .container{
      width: 800px;
      height: 500px;
      box-shadow: 0 15px 50px rgba(0, 0, 0, 0.6);
    }

    .user-login{

      .form-background{
        padding: 53px;
      }

      h2{
        font-size: 20px;
        letter-spacing: 3px;
        margin-bottom: 7px;
        top: 0;
      }

      h2:after{
        border-bottom:2px solid #000;
        top: 13px;

      }

      input {
        padding: 13px;
        font-size: 13px;
        letter-spacing: 2px;
        margin: 7px 0;
        top: 17px;
      }

      input[type="submit"]{
        max-width: 140px;
      }

      .login-btn{
        left: 13px;
      }

      .signup{
        margin-top: 200px;
        font-size: 8px;
        letter-spacing: 1px;
      }

      a{
        letter-spacing: 2px;
      }
    }

    .user-signup{

      .form-background{
        padding: 53px;
      }

      h2{
        font-size: 20px;
        letter-spacing: 3px;
        margin-bottom: 7px;
        top: 70px;
      }

      h2:after{
        border-bottom:2px solid #000;
        top: 13px;

      }

      input{
        padding: 13px;
        font-size: 13px;
        letter-spacing: 2px;
        margin: 7px 0;
        top: 87px;
      }

      input[type="submit"]{
        max-width: 140px;
      }

      .signup{
        margin-top: 187px;
        font-size: 13px;
        letter-spacing: 1px;
        top: -57px;
      }

      a{
        letter-spacing: 2px;
      }
    }
  }
}

@media (max-height: 1080px) {

  section{
    padding: 20px;

    .container{
      width: 600px;
      height: 375px;
      box-shadow: 0 7.5px 25px rgba(0, 0, 0, 0.6);
    }

    .user-login{

      .form-background{
        padding: 40px;
      }

      h2{
        font-size: 15px;
        letter-spacing: 2px;
        margin-bottom: 5px;
        top: -7.5px;
      }

      h2:after{
        border-bottom: 1px solid #000;
        top: 12.5px;

      }

      input{
        padding: 4px;
        font-size: 6px;
        letter-spacing: 2px;
        margin: 5px 0;
        top: 12.5px;
      }

      input[type="submit"]{
        max-width: 105px;
      }

      .login-btn{
        left: 10px;
      }

      .signup{
        position: relative;
        margin-top: 150px;
        font-size: 4px;
        letter-spacing: 1px;
      }

      a{
        letter-spacing: 1px;
      }
    }

    .user-signup{

      .form-background{
        padding: 40px;
      }

      h2{
        font-size: 15px;
        letter-spacing: 2px;
        margin-bottom: 5px;
        top: 37.5px;
      }

      h2:after{
        border-bottom: 1px solid #000;
        top: 12.5px;

      }

      input{
        padding: 4px;
        font-size: 6px;
        letter-spacing: 2px;
        margin: 5px 0;
        top: 57.5px;
      }

      input[type="submit"]{
        max-width: 105px;
      }

      .signup{
        margin-top: 140px;
        font-size: 4px;
        letter-spacing: 1px;
        top: -42.5px;
      }

      a{
        letter-spacing: 1px;
      }
    }
  }
}


</style>