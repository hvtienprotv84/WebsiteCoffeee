var predefinedReplies = {
  "hello": "Hi!",
  "hi": "Hello!",
  "how are you": "I'm fine, thank you!",
  "what your name": "My name is Chat Bot.",
  "bạn là ai": "Tôi là ChatBot Hân Hạnh Hỗ Trợ Quý Khách!!!",
  "ban la ai": "Tôi là ChatBot Hân Hạnh Hỗ Trợ Quý Khách!!!",
  "cà phê đen đá giá bao nhiêu": "Giá Cà Phê Đen Đá là 25.000 VNĐ",
  "ca phe den da gia bao nhieu": "Giá Cà Phê Đen Đá là 25.000 VNĐ",
  "cà phê đen đá giá bao nhiêu": "Giá Cà Phê Đen Đá là 25.000 VNĐ",
  "ca phe den da gia bao nhieu": "Giá Cà Phê Đen Đá là 25.000 VNĐ",
  // Thêm các câu trả lời khác ở đây
};
// Lấy các phần tử từ HTML
var chatLog = document.getElementById("chatLog");
var userInput = document.getElementById("userInput");
var closeButton = document.getElementById("closeButton");
var chatbotPopup = document.getElementById("chatbotPopup");

// Xử lý sự kiện khi người dùng gửi tin nhắn
function sendMessage() {
  var message = userInput.value.trim();
  if (message !== "") {
    addMessage("user", message);
    processMessage(message.toLowerCase());
    userInput.value = "";
  }
}

// Xử lý tin nhắn từ người dùng
function processMessage(message) {
  if (message in predefinedReplies) {
    var reply = predefinedReplies[message];
    setTimeout(function() {
      addMessage("bot", reply);
    }, 500);
  } else {
    setTimeout(function() {
      //addMessage("bot", "Nhập lại.");
      addMessage("bot", "Quý Khách Vui Lòng Liên Hệ HotLine 0931103224 <br> Email: tyncoffeestore@gmail.com <br> Xin Quý Khách Thông Cảm!!!.");
    }, 500);
  }
}

// Thêm tin nhắn vào khung chat
function addMessage(sender, message) {
  var messageElement = document.createElement("div");
  messageElement.classList.add(sender);
  messageElement.innerHTML = message;
  chatLog.appendChild(messageElement);
  chatLog.scrollTop = chatLog.scrollHeight;

  var avatarElement = document.createElement("img");
  avatarElement.classList.add(sender + "-avatar");
  avatarElement.src = (sender === "user") ? "https://cdn-icons-png.flaticon.com/512/1159/1159740.png" : "https://static.vecteezy.com/system/resources/previews/004/996/790/original/robot-chatbot-icon-sign-free-vector.jpg";

   if (sender === "user") {
    avatarElement.style.width = "40px"; // Thay đổi kích thước ảnh avatar người dùng theo ý muốn
    avatarElement.style.height = "40px"; // Thay đổi kích thước ảnh avatar người dùng theo ý muốn
    avatarElement.style.marginLeft  = "10px";

  } else {
    avatarElement.style.width = "50px"; // Thay đổi kích thước ảnh avatar chatbot theo ý muốn
    avatarElement.style.height = "50px"; // Thay đổi kích thước ảnh avatar chatbot theo ý muốn

  }

  messageElement.appendChild(avatarElement);


}


// Xử lý sự kiện khi người dùng nhấn Enter
userInput.addEventListener("keyup", function(event) {
  if (event.keyCode === 13) {
    sendMessage();
  }
});

// Xử lý sự kiện khi người dùng nhấn nút đóng
closeButton.addEventListener("click", function() {
  chatbotPopup.style.display = "none";
});

// Xử lý sự kiện khi người dùng nhấn nút mở
document.getElementById("openButton").addEventListener("click", function() {
  chatbotPopup.style.display = "block";
});

document.getElementById("sendButton").addEventListener("click", function() {
  sendMessage();
});