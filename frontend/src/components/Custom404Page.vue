<template>
  <div class="main">
    <span class="text">404</span>
    <h1>Maybe going home helps</h1>
    <a href="#">Home</a>
  </div>
</template>

<script>
export default {
  name: "Custom404Page"
}
</script>

<style lang="scss">

@import url('https://fonts.googleapis.com/css2?family=Montserrat:wght@100;200&display=swap');

*,
*::before,
*::after {
  box-sizing: border-box;
  margin: 0;
  padding: 0;
}

.main {
  height: 100vh;
  display: grid;
  justify-content: center;
  align-items: center;
  align-content: space-between;
  background-color: #213737;
}

.text {
  cursor: default;
  position: absolute;
  align-self: center;
  top: 25%;
  right: 50%;
  transform: translate(50%,50%);
  text-transform: uppercase;
  font-family: Montserrat, sans-serif;
  font-size: 16vh;
  font-weight: 700;
  color: #F5F5F5;

  text-shadow:
      1px 1px 1px #919191,
      1px 2px 1px #919191,
      1px 3px 1px #919191,
      1px 4px 1px #919191,
      1px 5px 1px #919191,
      1px 6px 1px #919191,
      1px 7px 1px #919191,
      1px 8px 1px #919191,
      1px 9px 1px #919191,
      1px 10px 1px #919191,
      1px 18px 6px rgba(16,16,16,0.4),
      1px 22px 10px rgba(16,16,16,0.2),
      1px 25px 35px rgba(16,16,16,0.2),
      1px 30px 60px rgba(16,16,16,0.4);
}

h1{
  color: #F5F5F5;
  position: relative;
  font-family: "Source Code Pro", monospace;
  font-size: 1.5vh;
  top: 53.5vh;
  font-weight: 400;
}

h1::before,
h1::after{
  content: "";
  position: absolute;
  top: 0;
  right: 0;
  bottom: 0;
  left: 0;
}

h1::before{
  background: #213737;
  animation: shifteffect 4s steps(22) forwards;
}

h1::after{
  width: 0.1vh;
  background: white;
  animation:
      shifteffect 4s steps(22) forwards,
      blinkeffect 600ms steps(22) infinite;
}

a{
  font-family: Montserrat, sans-serif;
  position: absolute;
  top: 4%;
  right: 5%;
  font-size: 2vh;
  text-transform: uppercase;
  text-decoration: none;
  color: #F5F5F5;
  transition: all 300ms;
}

a:hover{
  cursor: pointer;
  font-size: 2.5vh;
  transition: all 300ms;
}

@keyframes shifteffect{
  to{
    left: 100%;
  }
}

@keyframes blinkeffect{
  to{
    background: transparent;
  }
}

</style>