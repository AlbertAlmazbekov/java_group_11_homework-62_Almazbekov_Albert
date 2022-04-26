$('.message a').click(function () {
   $('form').animate({
      height: "toggle",
      opacity: "toggle"
   }, "slow");
});

$(() => {

   const baseUrl = 'http://localhost:8080';

   let createUser = async function (formData) {
      let userJSON = JSON.stringify(Object.fromEntries(formData))
      let url = '/users/registration'

      const settings = {
         method: 'POST',
         cache: 'no-cache',
         // mode: 'cors',
         headers: {
            'Content-Type': 'application/json'
          },
         body: userJSON
      }

      let response = await fetch(baseUrl + url, settings);
      let responseData = await response.json()
      console.log(responseData)
   }

   const registerForm = $('#register_form')

   registerForm.on('submit', e => {
      e.preventDefault()

      let data = e.target
      console.log(data)

      let formData = new FormData(data)

      createUser(formData)
   })
})