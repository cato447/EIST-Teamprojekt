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
            <a href="/">Storage</a>
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
                    <a href="#/login" v-on:click="isSignUp = !isSignUp">Sign up</a>
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
                <p class="signup">Have an account?
                  <br>
                  <a href="#/login" v-on:click="isSignUp = !isSignUp">Sign in</a>
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
  background: #006064;
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
    width: 2800px;
    height: 1700px;
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
      //background: url('../ressouce/5008bf96009ea69ba157815061bce4f2.png');
      background: brown;
    }

    .form-background {
      position: relative;
      width: 50%;
      height: 100%;
      background: white;
      display: flex;
      justify-content: center;
      align-items: center;
      padding: 160px;
    }

    h2 {
      position: relative;
      font-size: 60px;
      font-weight: 600;
      text-transform: uppercase;
      letter-spacing: 8px;
      text-align: center;
      width: 100%;
      margin-bottom: 20px;
      color: black;
      top: -30px;
    }

    h2:after {
      content: '';
      display: block;
      border-bottom: 4px solid #000;
      height: 0;
      position: relative;
      top: 50px;

    }

    input {
      position: relative;
      width: 100%;
      padding: 40px;
      background: lightgrey;
      border: none;
      outline: none;
      box-shadow: none;
      font-size: 1vh;
      letter-spacing: 4px;
      margin: 20px 0;
      top: 50px;
      color: black;
    }

    input[type="submit"] {
      cursor: pointer;
      max-width: 520px;
      transition: 0.5s;
      background-color: darkslategrey;
      color: white;
    }

    .login-btn {
      position: relative;
      left: 40px;
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
      justify-content: right;
      display: flex;
      position: relative;
      margin-top: 550px;
      top: 50px;
      font-size: 40px;
      text-transform: uppercase;
      letter-spacing: 4px;
      //border: 1px solid black;
    }

    a {
      font-weight: 600;
      text-decoration: none;
      color: darkslategrey;
      letter-spacing: 2px;
      font-size: 50px;
      transition: color 0.5s ease-in-out;
      //border: 1px solid black;
      width: 100%;
      text-align: left;
      justify-content: center;
      display: flex;
      left: 30%;
      top: -8px;
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
      //background: url('../ressouce/5008bf96009ea69ba157815061bce4f2.png');
      background: brown;
    }

    .form-background {
      position: relative;
      width: 50%;
      height: 100%;
      background: white;
      display: flex;
      justify-content: center;
      align-items: center;
      padding: 160px;
      top: -100%;
    }

    h2 {
      position: relative;
      font-size: 60px;
      font-weight: 600;
      text-transform: uppercase;
      letter-spacing: 8px;
      text-align: center;
      width: 100%;
      margin-bottom: 20px;
      color: black;
      top: 150px;
    }

    h2:after {
      content: '';
      display: block;
      border-bottom: 4px solid #000;
      height: 0;
      position: relative;
      top: 50px;

    }

    input {
      position: relative;
      width: 100%;
      padding: 40px;
      background: lightgrey;
      border: none;
      outline: none;
      box-shadow: none;
      font-size: 40px;
      letter-spacing: 4px;
      margin: 20px 0;
      top: 230px;
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
      max-width: 520px;
      transition: 0.5s;
      background-color: darkslategrey;
      color: white;
      left: 51.8%;
    }

    .signup {
      text-align: center;
      justify-content: left;
      display: flex;
      position: relative;
      margin-top: 560px;
      font-size: 20px;
      text-transform: uppercase;
      letter-spacing: 4px;
      top: -130px;
      //border: 1px solid black;
    }

    a {
      text-align: center;
      justify-content: center;
      display: flex;
      width: 100%;
      font-weight: 600;
      text-decoration: none;
      color: darkslategrey;
      letter-spacing: 2px;
      font-size: 50px;
      top: -10px;
      left: 30%;
      transition: color 0.5s ease-in-out;
      //border: 1px solid black;
    }

    a:hover{
      color: brown;
    }
  }
}

@media (max-device-height: 1440px) {

  /* login / signin styling */

  section {
    padding: 52px;

    .container {
      width: 1800px;
      height: 1200px;
      box-shadow: 0 15px 50px rgba(0, 0, 0, 0.6);
    }

    .user-login {

      .form-background {
        padding: 106px;
      }

      h2 {
        font-size: 40px;
        letter-spacing: 6px;
        margin-bottom: 14px;
        top: -40px;
      }

      h2:after {
        border-bottom: 4px solid #000;
        top: 26px;

      }

      input {
        padding: 26px;
        letter-spacing: 4px;
        margin: 14px 0;
        top: -4px;
      }

      input[type="submit"] {
        max-width: 330px;
      }

      .login-btn {
        left: 28px;
      }

      .signup {
        margin-top: 400px;
        letter-spacing: 2px;
        font-size: 20px;
        top: 20px;
        justify-content: center;
      }

      a {
        letter-spacing: 4px;
        font-size: 40px;
        top: 50px;
        width: 100%;
        left: 0;
      }
    }

    .user-signup {

      .form-background {
        padding: 106px;
      }

      h2 {
        font-size: 40px;
        letter-spacing: 6px;
        margin-bottom: 140px;
        top: 120px;
      }

      h2:after {
        border-bottom: 4px solid #000;
        top: 26px;

      }

      input {
        padding: 26px;
        font-size: 26px;
        letter-spacing: 4px;
        margin: 14px 0;
        top: 27px;
      }

      input[type="submit"] {
        max-width: 330px;
        left: 52%;
      }

      .signup {
        margin-top: 374px;
        letter-spacing: 2px;
        top: -154px;
        font-size: 20px;
        justify-content: center;
      }

      a {
        letter-spacing: 4px;
        width: 100%;
        left: 0;
        font-size: 40px;
        top: 50px;
      }
    }
  }
}

@media (max-device-height: 1080px) {

  /* login / signin styling */

  section {
    padding: 40px;

    .container {
      width: 1500px;
      height: 900px;
      box-shadow: 0 7.5px 25px rgba(0, 0, 0, 0.6);
    }

    .user-login {

      .form-background {
        padding: 80px;
      }

      h2 {
        font-size: 30px;
        letter-spacing: 4px;
        margin-bottom: 10px;
        top: -25px;
      }

      h2:after {
        border-bottom: 2px solid #000;
        top: 25px;

      }

      input {
        padding: 12px;
        font-size: 12px;
        letter-spacing: 2px;
        margin: 10px 0;
        top: 15px;
      }

      input[type="submit"] {
        max-width: 285px;
      }

      .login-btn {
        left: 20px;
      }

      .signup {
        position: relative;
        margin-top: 300px;
        letter-spacing: 2px;
        font-size: 0.05vh;
        align-content: center;
      }

      a {
        letter-spacing: 1px;
        align-content: center;
        font-size: 40px;
      }
    }

    .user-signup {

      .form-background {
        padding: 80px;
      }

      h2 {
        font-size: 30px;
        letter-spacing: 4px;
        margin-bottom: 10px;
        top: 50px;
      }

      h2:after {
        border-bottom: 2px solid #000;
        top: 25px;

      }

      input {
        padding: 12px;
        font-size: 12px;
        letter-spacing: 2px;
        margin: 10px 0;
        top: 90px;
      }

      input[type="submit"] {
        max-width: 260px;
        left: 55.8%;
      }

      .signup {
        margin-top: 280px;
        letter-spacing: 2px;
        top: -60px;
        font-size: 30px;
      }

      a {
        letter-spacing: 2px;
        font-size: 40px;
        align-content: center;
      }
    }
  }
}

</style>