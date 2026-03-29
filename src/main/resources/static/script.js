

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