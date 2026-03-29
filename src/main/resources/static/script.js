

function sendMessage(){
    let message = document.getElementById("user-input").value
    console.log("This is your message: ", message)
    addToLogUser(message)



}

function addToLogUser(message){
    
    const chatBox = document.getElementById("chat-box")
    const newDiv = document.createElement("div")

    const userText = document.createTextNode("USER --> " + message)
    newDiv.setAttribute("id", "user-message")
    newDiv.appendChild(userText)
    chatBox.appendChild(newDiv)

}

function messageReq(){
        fetch(`http://localhost:8080/api/chat/message`)
        .then(response => {

            if (!response.ok) {
                throw new Error("Could not fetch resource");

            }
            //Have to return the result of the promise to the local browser?
            return response.json()
        })

        .then(data => {

            console.log(data)

        })
        .catch(error => console.error(error))
}
