'use strict';

var usernamePage = document.querySelector('#username-page');
var chatPage = document.querySelector('#chat-page');
var usernameForm = document.querySelector('#usernameForm');
var messageInput = document.querySelector('#message');
var messageArea = document.querySelector('#messageArea');
var btnSend= document.querySelector('#btnSend');
var images = document.querySelectorAll('img');

var stompClient = null;
var username = null;
var imageUrl = null;
var idUser = null;



function handleClickImage(e)
{
	imageUrl = e.src;
    //console.log(e);
    images.forEach(image => {
		image.style.width = '30%';
	});
	
	images.forEach(image =>
	{
		if(image.src === e.src)
		{
			image.style.width = '35%';
		}
	})
}

document.addEventListener('DOMContentLoaded', function()
{
	 var chatBox = document.getElementById('messageArea'); 
     var mobileHeight = window.innerHeight;

    chatBox.style.height = mobileHeight - 150 + 'px';
})

window.addEventListener('resize', function() {
 
 var chatBox = document.getElementById('messageArea'); 
    var mobileHeight = window.innerHeight;
    chatBox.style.height = mobileHeight - 150 + 'px';
});


function renderChatPage()
{
	  $.get(`/chat`, function (responseJson) {
        var divMess = document.createElement('div');
        var text = ``;
        for(let i = 0 ; i < responseJson.length; i++)
        {
			if(responseJson[i].idUser != idUser)
			{
				text += `
	        <div class="d-flex justify-content-between">
                <p class="small mb-1">${responseJson[i].name}</p>
                <p class="small mb-1 text-muted">${new Date().toDateString()}</p>
              </div>
              <div class="d-flex flex-row justify-content-start">
                <img
                  src="${responseJson[i].image}"
                  alt="avatar 1"
                  style="width: 45px; height: 100%"
                />
                <div>
                  <p
                    class="small p-2 ms-3 mb-3 rounded-3"
                    style="background-color: #f5f6f7"
                  >
                    ${responseJson[i].content}
                  </p>
                </div>
              </div>
	        `  
			}
			else{
				text += `
	        <div class="d-flex justify-content-between">
      
                <p class="small mb-1 text-muted">${new Date().toDateString()}</p>
                 <p class="small mb-1">${responseJson[i].name}</p>
              </div>
              <div class="d-flex flex-row justify-content-end">
                <div>
                  <p
                    class="small p-2 ms-3 mb-3 rounded-3"
                    style="background-color: #f5f6f7"
                  >
                    ${responseJson[i].content}
                  </p>
                </div>
                 <img
                  src="${responseJson[i].image}"
                  alt="avatar 1"
                  style="width: 45px; height: 100%"
                />
              </div>
	        `  
			}
		}
        
     
	      divMess.innerHTML = text;
	      messageArea.appendChild(divMess);
          messageArea.scrollTop = messageArea.scrollHeight;
        }).fail(function () {
            alert("FAIL")
        }
        );
}


function connect(event) {
	event.preventDefault();
    username = document.querySelector('#name').value.trim();
    
    if(!imageUrl)
    {
		alert("Hãy chọn ảnh đại diện");
		return;
	}
	
	if(!username)
	{
		alert("Hãy nhập tên");
		return;
	}
    
    if(username && imageUrl) {
		
		var data = {};
		data['name'] = username;
		data['image'] = imageUrl;
			
	    //console.log(JSON.stringify(data));
	    
		// add user
		$.ajax({
            url: '/user',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function (result) {
				idUser = result.id;
                usernamePage.classList.add('hidden');
                chatPage.classList.remove('hidden');
                renderChatPage();
                var socket = new SockJS('/ws');
                stompClient = Stomp.over(socket);
                stompClient.connect({}, onConnected, onError);
            },
            error: function (error) {
                alert("Fail");
            	window.location.href = "[[@{'/'}]]";
            }
        });
	
    }
   
}



function onConnected() {
	

    // Subscribe to the Public Topic
    stompClient.subscribe('/topic/public', onMessageReceived);

    // Tell your username to the server
    stompClient.send("/app/chat.addUser",
        {},
        JSON.stringify({ type: 'JOIN',idUser: idUser })
    )
}


function onError(error) {
   console.log("Error");
}


function sendMessage(event) {
    var messageContent = messageInput.value.trim();
    if(messageContent && stompClient) {
        var chatMessage = {
            content: messageInput.value,
            type: 'CHAT',
            idUser : idUser,
            name : username,
            image : imageUrl
        };
        stompClient.send("/app/chat.sendMessage", {}, JSON.stringify(chatMessage));
        messageInput.value = '';
    }
    event.preventDefault();
}


function onMessageReceived(payload) {
    var message = JSON.parse(payload.body);

    if(message.type === 'JOIN') {
        //messageElement.classList.add('event-message');
        //message.content = message.sender + ' joined!';
    } else if (message.type === 'LEAVE') {
        //messageElement.classList.add('event-message');
        //message.content = message.sender + ' left!';
    } else {
      
	var divMess = document.createElement('div');
	if(idUser != message.idUser)
	{
		 divMess.innerHTML  = `
	        <div class="d-flex justify-content-between">
                <p class="small mb-1">${message.name}</p>
                <p class="small mb-1 text-muted">${new Date().toDateString()}</p>
              </div>
              <div class="d-flex flex-row justify-content-start">
                <img
                  src="${message.image}"
                  alt="avatar 1"
                  style="width: 45px; height: 100%"
                />
                <div>
                  <p
                    class="small p-2 ms-3 mb-3 rounded-3"
                    style="background-color: #f5f6f7"
                  >
                    ${message.content}
                  </p>
                </div>
              </div>
	        `  
	}
	else{
	divMess.innerHTML = `
	<div class="d-flex justify-content-between">
                
                <p class="small mb-1 text-muted">${new Date().toDateString()}</p>
                <p class="small mb-1">${message.name}</p>
              </div>
              <div class="d-flex flex-row justify-content-end"> 
                <div>
                  <p
                    class="small p-2 ms-3 mb-3 rounded-3"
                    style="background-color: #f5f6f7"
                  >
                    ${message.content}
                  </p>
                </div>
                <img
                  src="${message.image}"
                  alt="avatar 1"
                  style="width: 45px; height: 100%"
                />
              </div>
	        `  
	}
	
	       messageArea.appendChild(divMess);       
    }
    messageArea.scrollTop = messageArea.scrollHeight;
}


function getAvatar(messageSender) {
    var hash = 0;
    for (var i = 0; i < messageSender.length; i++) {
        hash = 31 * hash + messageSender.charCodeAt(i);
    }
    var index = Math.abs(hash % 6) + 1;
    return `https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-chat/ava${index}-bg.webp`;
}

usernameForm.addEventListener('submit', connect, true)
//messageForm.addEventListener('submit', sendMessage, true)
btnSend.addEventListener('click', sendMessage)

