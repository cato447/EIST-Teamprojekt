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

        <input id="menu-btn" class="menu-btn" type="checkbox">
        <label class="menu-icon" for="menu-btn">
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

          <div id="user-sign-in" class="user-login">
            <div class="site-background"/>
            <div class="form-background">
              <form>

                <h2>Sign In</h2>
                <input placeholder="Username" type="text">
                <input placeholder="Password" type="password">
                <input class="login-btn-apple" type="submit" value="Login Apple">
                <input class="login-btn" type="submit" value="Login">
                <p class="signup">Don't have an account?
                  <a href="#" v-on:click="isSignUp = !isSignUp">Sign up</a>
                </p>

              </form>
            </div>
          </div>

          <div :class="{toggleOff:isSignUp}" class="user-signup">
            <div class="site-background"/>
            <div class="form-background">
              <form>

                <h2>Create Account</h2>
                <input placeholder="Username" type="text">
                <input placeholder="Email Address" type="text">
                <input placeholder="Create Password" type="password">
                <input placeholder="Confirm Password" type="password">
                <input type="submit" value="Sign Up">
                <p class="signup">Already have an account?
                  <br>
                  <a href="#" v-on:click="isSignUp = !isSignUp">Sign in</a>
                </p>

              </form>
            </div>
          </div>

        </div>
      </section>

      <div class="footer">
        <div class="container">
          <div class="row">

            <div class="footer-col">
              <h4 class="firstHeadline">About Us</h4>
              <ul>
                <li class="aboutUs">
                  <a href="#">The Project</a>
                  <a href="#">The Team</a>
                  <a href="#">Our Partners</a>
                </li>
              </ul>
            </div>

            <div class="footer-col">
              <h4 class="secondHeadline">Help</h4>
              <ul>
                <li class="help">
                  <a href="#">Support</a>
                  <a href="#">FAQ</a>
                  <a href="#">AGB</a>
                </li>
              </ul>
            </div>

            <div class="footer-col">
              <h4 class="thirdHeadline">Follow Us</h4>
              <ul>
                <li class="social-links">
                  <a href="#">
                    <i class="fa fa-instagram fa-2x"/>
                  </a>
                </li>
              </ul>
            </div>

          </div>
        </div>
      </div>

    </div>

    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.3.0/css/font-awesome.css" rel="stylesheet"
          type='text/css'/>
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
  list-style: none;
}

.main {
  font-family: 'Montserrat', sans-serif;
  height: 100vh;
  display: grid;
  justify-content: center;
  align-items: center;
  background: #213737;
}

.logo a {
  color: white;
}

.menu-icon {
  &__line, &__line:before, &__line::after {
    background-color: white;
  }
}


/* footer styling */

.footer {
  position: fixed;
  display: flex;
  left: 0;
  top: 90%;
  width: 100vw;
  height: 8vh;
  z-index: 3;
  background: white;
}

.container {
  position: relative;
  background: darkorange;
  padding: 40px;
  width: 100%;
  height: 96%;
  margin: auto;
}

.row {
  display: flex;
  flex-wrap: wrap;
}

.footer-col {
  width: 25%;
}

.footer-col h4 {
  text-align: center;
  text-transform: uppercase;
  position: relative;
  letter-spacing: 2px;
  font-size: 24px;
  color: white;
  left: 50%;
  top: -20px;
}


.footer-col h4::before {
  content: '';
  position: absolute;
  bottom: -10px;
  background-color: black;
  height: 2px;
  box-sizing: border-box;
  width: 65px;
}

.footer-col ul li a {
  position: sticky;
  text-align: left;
  font-size: 20px;
  width: 140px;
  text-decoration: none;
  font-weight: 300;
  color: white;
  display: block;
  transition: all 0.3s ease;
  left: 8000%;
  margin: 0 -60px;
}

.aboutUs {
  position: relative;
  left: 6px;
}

.help {
  position: relative;
  left: 44px;
}

.social-links {
  position: relative;
  left: -13.5px;
  top: 5px;
}

.fa-instagram {
  position: relative;
  top: 14px;
  color: black;
  left: 1px;
}

.footer-col .social-links a {
  height: 65px;
  width: 65px;
  background-color: rgba(255, 255, 255, 0.2);
  box-shadow: 0 15px 50px rgba(0, 0, 0, 0.1);
  margin: 0 10px 10px 0;
  row-gap: 10px;
  text-align: center;
  border-radius: 50%;
  color: black;
  transition: all 0.5s ease;
}

.footer-col h4:hover {
  cursor: default;
}

.footer-col .aboutUs a:hover {
  color: black;
  padding-left: 8px;
}

.footer .help a:hover {
  color: black;
  padding-left: 8px;
}

.footer-col .social-links a:hover {
  color: #24262b;
  background-color: #ffffff;
}

/* login / signin styling */

section {
  position: relative;
  min-height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 40px;
  transition: 0.5s;

  .container {
    position: relative;
    width: 1200px;
    height: 750px;
    background: white;
    box-shadow: 0 15px 50px rgba(0, 0, 0, 0.6);
  }

  .user-login {
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    display: flex;

    .site-background {
      position: relative;
      width: 50%;
      height: 100%;
      transition: 0.5s;
      background: url('../ressouce/5008bf96009ea69ba157815061bce4f2.png');
    }

    .form-background {
      position: relative;
      width: 50%;
      height: 100%;
      background: white;
      display: flex;
      justify-content: center;
      align-items: center;
      padding: 80px;
    }

    h2 {
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

    h2:after {
      content: '';
      display: block;
      border-bottom: 2px solid #000;
      height: 0;
      position: relative;
      top: 25px;

    }

    input {
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

    input[type="submit"] {
      cursor: pointer;
      max-width: 210px;
      transition: 0.5s;
      background-color: darkslategrey;
      color: white;
    }

    .login-btn {
      position: relative;
      left: 20px;
    }

    .signup {
      text-align: center;
      position: relative;
      margin-top: 300px;
      font-size: 20px;
      text-transform: uppercase;
      letter-spacing: 2px;
    }

    a {
      font-weight: 600;
      text-decoration: none;
      color: darkslategrey;
      letter-spacing: 2px;
    }
  }

  .user-signup {
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;

    .site-background {
      position: relative;
      width: 50%;
      height: 100%;
      left: 50%;
      transition: 0.5s;
      background: url('../ressouce/5008bf96009ea69ba157815061bce4f2.png');
    }

    .form-background {
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

    h2 {
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

    h2:after {
      content: '';
      display: block;
      border-bottom: 2px solid #000;
      height: 0;
      position: relative;
      top: 25px;

    }

    input {
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

    input[type="submit"] {
      cursor: pointer;
      max-width: 210px;
      transition: 0.5s;
      background-color: darkslategrey;
      color: white;
      left: 52%;
    }

    .signup {
      text-align: center;
      position: relative;
      margin-top: 280px;
      font-size: 20px;
      text-transform: uppercase;
      letter-spacing: 2px;
      top: -85px;
    }

    a {
      text-align: center;
      font-weight: 600;
      text-decoration: none;
      color: darkslategrey;
      letter-spacing: 2px;
    }
  }
}

.toggleOff {
  display: none;
}

@media (max-height: 1440px) {

  /* footer styling */

  .footer {
    position: fixed;
    display: flex;
    left: 0;
    top: 87.5%;
    width: 100vw;
    height: 10.5vh;
    z-index: 3;
    background: white;
  }

  .footer-col h4 {
    letter-spacing: 2px;
    font-size: 12px;
  }

  .footer-col h4::before {
    bottom: -7px;
    height: 2px;
    width: 43px;
  }

  .footer-col ul li a {
    font-size: 12px;
    width: 120px;
    top: 13px;
  }

  .aboutUs {
    position: relative;
    left: 2px;
    top: -6px;
  }

  .help {
    left: 30px;
    top: -6px;
  }

  .social-links {
    left: -23px;
    top: 5px;
  }

  .fa-instagram {
    top: 2px;
    left: 0.5px;
  }

  .footer-col .social-links a {
    height: 37px;
    width: 37px;
    margin: 0 7px 7px 0;
    line-height: 27px;
    top: 20px;
    left: 76%;
  }

  .footer-col .aboutUs a:hover {
    padding-left: 5px;
  }

  .footer .help a:hover {
    padding-left: 5px;
  }

  /* login / signin styling */

  section {
    padding: 27px;

    .container {
      width: 800px;
      height: 500px;
      box-shadow: 0 15px 50px rgba(0, 0, 0, 0.6);
    }

    .user-login {

      .form-background {
        padding: 53px;
      }

      h2 {
        font-size: 20px;
        letter-spacing: 3px;
        margin-bottom: 7px;
        top: 0;
      }

      h2:after {
        border-bottom: 2px solid #000;
        top: 13px;

      }

      input {
        padding: 13px;
        font-size: 13px;
        letter-spacing: 2px;
        margin: 7px 0;
        top: 17px;
      }

      input[type="submit"] {
        max-width: 140px;
      }

      .login-btn {
        left: 13px;
      }

      .signup {
        margin-top: 200px;
        font-size: 8px;
        letter-spacing: 1px;
      }

      a {
        letter-spacing: 2px;
      }
    }

    .user-signup {

      .form-background {
        padding: 53px;
      }

      h2 {
        font-size: 20px;
        letter-spacing: 3px;
        margin-bottom: 7px;
        top: 70px;
      }

      h2:after {
        border-bottom: 2px solid #000;
        top: 13px;

      }

      input {
        padding: 13px;
        font-size: 13px;
        letter-spacing: 2px;
        margin: 7px 0;
        top: 87px;
      }

      input[type="submit"] {
        max-width: 140px;
      }

      .signup {
        margin-top: 187px;
        font-size: 13px;
        letter-spacing: 1px;
        top: -57px;
      }

      a {
        letter-spacing: 2px;
      }
    }
  }
}

@media (max-height: 1080px) {

  /* footer styling */

  .footer {
    position: fixed;
    display: flex;
    left: 0;
    top: 83.5%;
    width: 100vw;
    height: 14vh;
    z-index: 3;
    background: white;
  }

  .footer-col h4 {
    letter-spacing: 1px;
    font-size: 12px;
  }

  .footer-col h4::before {
    bottom: -5px;
    height: 1px;
    width: 32.5px;
  }

  .footer-col ul li a {
    font-size: 10px;
    width: 118px;
    top: 10px;
    left: 78%;
  }

  .aboutUs {
    position: relative;
    left: 2px;
    top: -6px;
  }

  .help {
    left: 30px;
    top: -6px;
  }

  .social-links {
    left: -23px;
    top: -1px;
  }

  .fa-instagram {
    top: -4px;
    left: 0.5px;
  }

  .footer-col .social-links a {
    height: 37px;
    width: 37px;
    margin: 0 7px 7px 0;
    line-height: 27px;
    top: 20px;
    left: 76%;
  }

  .footer-col .aboutUs a:hover {
    padding-left: 5px;
  }

  .footer .help a:hover {
    padding-left: 5px;
  }

  /* login / signin styling */

  section {
    padding: 20px;

    .container {
      width: 600px;
      height: 375px;
      box-shadow: 0 7.5px 25px rgba(0, 0, 0, 0.6);
    }

    .user-login {

      .form-background {
        padding: 40px;
      }

      h2 {
        font-size: 15px;
        letter-spacing: 2px;
        margin-bottom: 5px;
        top: -7.5px;
      }

      h2:after {
        border-bottom: 1px solid #000;
        top: 12.5px;

      }

      input {
        padding: 4px;
        font-size: 6px;
        letter-spacing: 2px;
        margin: 5px 0;
        top: 12.5px;
      }

      input[type="submit"] {
        max-width: 105px;
      }

      .login-btn {
        left: 10px;
      }

      .signup {
        position: relative;
        margin-top: 150px;
        font-size: 4px;
        letter-spacing: 1px;
      }

      a {
        letter-spacing: 1px;
      }
    }

    .user-signup {

      .form-background {
        padding: 40px;
      }

      h2 {
        font-size: 15px;
        letter-spacing: 2px;
        margin-bottom: 5px;
        top: 37.5px;
      }

      h2:after {
        border-bottom: 1px solid #000;
        top: 12.5px;

      }

      input {
        padding: 4px;
        font-size: 6px;
        letter-spacing: 2px;
        margin: 5px 0;
        top: 57.5px;
      }

      input[type="submit"] {
        max-width: 105px;
      }

      .signup {
        margin-top: 140px;
        font-size: 4px;
        letter-spacing: 1px;
        top: -42.5px;
      }

      a {
        letter-spacing: 1px;
      }
    }
  }
}

</style>