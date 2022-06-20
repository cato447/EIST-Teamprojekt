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

          <div class="user-login">
            <div class="site-background">Placeholder Logo</div>
            <div class="form-background">
              <form>

                <h2>Sign In</h2>
                <input type="text" placeholder="Username">
                <input type="password" placeholder="Password">
                <input type="submit" class="login-btn-apple" value="Login Apple">
                <input type="submit" class="login-btn" value="Login">
                <p class="signup">Don't have an account?
                  <a href="#" onclick="toggleForm()">Sign up</a>
                </p>

              </form>
            </div>
          </div>

          <div class="user-signup">
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
                  <a href="#" onclick="toggleForm()">Sign in</a>
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
  toggleForm(){
    const container = document.querySelector('.container');
    container.classList.toggle('active');
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
      background: dimgrey;
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
      font-size: 30px;
      font-weight: 600;
      text-transform: uppercase;
      letter-spacing: 4px;
      text-align: center;
      width: 100%;
      margin-bottom: 10px;
      color: black;
    }

    h2:after{
      content:'';
      display:block;
      border-bottom:2px solid #000;
      height:0;
      position:relative;
      top: 20px;

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
      top: 40px;
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
    top: -400px;
    left: 0;
    width: 100%;
    height: 100%;
    //display: flex;
    display: none;

    input[type="submit"]{
      cursor: pointer;
      max-width: 200px;
      font-weight: 500;
      transition: 0.5s;
      background: #213737;
    }

    .site-background{
      top: -100%;
    }

    .form-background{
      top: 100%;
    }

    .active .form-background{
      top: -100%;
    }

    .active .site-background{
      top: 0;
    }
  }
}

</style>