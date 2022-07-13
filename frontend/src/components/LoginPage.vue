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

          <div :style="{'display': isSignUp ? 'none' : ''}" class="user-signup">
            <div class="site-background"/>
            <div class="form-background">
              <form>

                <h2>Create Account</h2>
                <input placeholder="Username" type="text">
                <input placeholder="Email Address" type="text">
                <input placeholder="Create Password" type="password">
                <input placeholder="Confirm Password" type="password">
                <input type="submit" value="Sign Up" class="submit">
                <p class="signup">Already have an account?
                  <br>
                  <a href="#" v-on:click="isSignUp = !isSignUp">Sign in</a>
                </p>

              </form>
            </div>
          </div>

        </div>
      </section>

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

.logo {
  left: -3vh;
  top: 1vh;
}

.logo a {
  color: white;
}

.menu-icon {
  left: 3vh;
  top: 1vh;

  &__line, &__line:before, &__line::after {
    background-color: white;
  }
}

/* login / signin styling */

section {
  position: relative;
  min-height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 40px;

  .container {
    position: relative;
    width: 1400px;
    height: 850px;
    background: white;
    box-shadow: 0 15px 50px rgba(0, 0, 0, 0.6);
  }

  .user-login {
    position: relative;
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
      font-size: 1vh;
      letter-spacing: 2px;
      margin: 10px 0;
      top: 25px;
      color: black;
    }

    input[type="submit"] {
      cursor: pointer;
      max-width: 260px;
      transition: 0.5s;
      background-color: darkslategrey;
      color: white;
    }

    .login-btn {
      position: relative;
      left: 20px;
      box-shadow: 10px 10px 30px rgba(0, 0, 0, 0.2);
    }

    .login-btn-apple {
      box-shadow: 10px 10px 30px rgba(0, 0, 0, 0.2);
    }

    .login-btn:hover, .login-btn-apple:hover {
      background: brown;
      color: black;
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
      letter-spacing: 1px;
      transition: color 0.5s ease-in-out;
    }

    a:hover{
      color: brown;
    }
  }

  .user-signup {
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    transition: display 0.5s ease-in-out;

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

    .submit{
      box-shadow: 10px 10px 30px rgba(0, 0, 0, 0.2);
    }

    .submit:hover{
      background: brown;
      color: black;
    }

    input[type="submit"] {
      cursor: pointer;
      max-width: 260px;
      transition: 0.5s;
      background-color: darkslategrey;
      color: white;
      left: 51.8%;
    }

    .signup {
      text-align: center;
      position: relative;
      margin-top: 280px;
      font-size: 20px;
      text-transform: uppercase;
      letter-spacing: 2px;
      top: -65px;
    }

    a {
      text-align: center;
      font-weight: 600;
      text-decoration: none;
      color: darkslategrey;
      letter-spacing: 1px;
      transition: color 0.5s ease-in-out;
    }

    a:hover{
      color: brown;
    }
  }
}

@media (max-height: 1440px) {

  /* login / signin styling */

  section {
    padding: 27px;

    .container {
      width: 900px;
      height: 600px;
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
        letter-spacing: 2px;
        margin: 7px 0;
        top: 17px;
      }

      input[type="submit"] {
        max-width: 165px;
      }

      .login-btn {
        left: 14px;
      }

      .signup {
        margin-top: 200px;
        letter-spacing: 1px;
        font-size: 0.1vh;
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
        top: 60px;
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
        top: 77px;
      }

      input[type="submit"] {
        max-width: 165px;
        left: 52%;
      }

      .signup {
        margin-top: 187px;
        letter-spacing: 1px;
        top: -57px;
        font-size: 0.1vh;
      }

      a {
        letter-spacing: 2px;
      }
    }
  }
}

@media (max-height: 1080px) {

  /* login / signin styling */

  section {
    padding: 20px;

    .container {
      width: 700px;
      height: 425px;
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
        max-width: 130px;
      }

      .login-btn {
        left: 10px;
      }

      .signup {
        position: relative;
        margin-top: 150px;
        letter-spacing: 1px;
        font-size: 0.1vh;
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
        top: 25px;
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
        top: 45px;
      }

      input[type="submit"] {
        max-width: 130px;
      }

      .signup {
        margin-top: 140px;
        letter-spacing: 1px;
        top: -30px;
        font-size: 0.1vh;
      }

      a {
        letter-spacing: 1px;
      }
    }
  }
}

</style>