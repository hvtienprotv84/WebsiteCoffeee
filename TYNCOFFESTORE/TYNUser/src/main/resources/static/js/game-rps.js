  // Your web app's Firebase configuration.

  var firebaseConfig = {
      apiKey: "AIzaSyDpFQoRmHCvBX4l8AX8Gk1kYRn564e05II",
      authDomain: "tyncoffestore-game-rps.firebaseapp.com",
      projectId: "tyncoffestore-game-rps",
      storageBucket: "tyncoffestore-game-rps.appspot.com",
      messagingSenderId: "425558093031",
      appId: "1:425558093031:web:2321d9712e5a99dd65c642",
  }
  // Initialize Firebase.
  firebase.initializeApp(firebaseConfig);

  // Create a variable to reference the database.
  var database = firebase.database();

/*
// Your web app's Firebase configuration
// For Firebase JS SDK v7.20.0 and later, measurementId is optional
const firebaseConfig = {
  apiKey: "AIzaSyDpFQoRmHCvBX4l8AX8Gk1kYRn564e05II",
  authDomain: "tyncoffestore-game-rps.firebaseapp.com",
  projectId: "tyncoffestore-game-rps",
  storageBucket: "tyncoffestore-game-rps.appspot.com",
  messagingSenderId: "425558093031",
  appId: "1:425558093031:web:2321d9712e5a99dd65c642",
  measurementId: "G-CHSHFP0QG4"
};

// Initialize Firebase
const app = initializeApp(firebaseConfig);
const analytics = getAnalytics(app);
*/


  var p1Name = "";
  var p1Choice = "";
  var p1Wins = 0;
  var p1Losses = 0;
  var p1Access = false;
  var p2Name = "";
  var p2Choice = "";
  var p2Wins = 0;
  var p2Losses = 0;
  var p2Access = false;
  var isSpectating = false;
  var ties = 0;
  var arenaOpen = false;
  var specName = "";
  var whoAmI = ""; //player name
  var whatAmI = ""; // if they are player1[P1], player2[P2] or the spectator[Spectator]
  var myChoice = "";
  var chatMessage = "";
  var p1Exists = false;
  var p2Exists = false;
  var con2;

  $("#announcer").html("Two players needed to play!");

  // If enter -> "keyCode === 13" is pressed in the $("#player-name-entry") input field act as if it was submitted
  var userInput = document.getElementById("player-name-entry");
  userInput.addEventListener("keyup", function (event) {
      if (event.keyCode === 13) {
          event.preventDefault();
          document.getElementById("player-name-entry-submit").click();
      }
  });

  // Take player 1 seat
  function takeP1() {
      var currentName = "";
      var p = "";
      p1Name = whoAmI;
      currentName = p1Name;
      p = "Player 1";
      $("#p1-name").html(p1Name);
      p1Access = true;
      whatAmI = "P1";
      database.ref("/RPSp1").set({
          p1NameKey: p1Name
      });

      if (p1Name != "" && p2Name != "" && arenaOpen == false) {
          arenaOpen = true;
      }
      //Display what the user is
      $("#now-playing").empty();
      if (p1Access == true || p2Access == true) {
          $("#now-playing").append().html("<h1 class='text-center'>You are playing as " + currentName + "</h1>");
      } else {
          $("#now-playing").append().html("<h1 class='text-center'>You are spectating as " + currentName + "</h1>");
      }

      whoAmI = currentName;


      if (whatAmI == "P1") {
          con2 = database.ref("/RPSp1").push(whoAmI);
      } else if (whatAmI == "P2") {
          con2 = database.ref("/RPSp2").push(whoAmI);
      } else {
          con2 = database.ref("/RPSspec").push(whoAmI);
      }
      con2.onDisconnect().remove();
      return con2;
  }

  // Take player 2 seat
  function takeP2() {
      var currentName = "";
      var p = "";
      p2Name = whoAmI;
      currentName = p2Name;
      p = "Player 2";
      $("#p2-name").html(p2Name);
      p2Access = true;
      whatAmI = "P2";
      database.ref("/RPSp2").set({
          p2NameKey: p2Name
      });

      if (p1Name != "" && p2Name != "" && arenaOpen == false) {
          arenaOpen = true;
      }
      //Display what the user is
      $("#now-playing").empty();
      if (p1Access == true || p2Access == true) {
          $("#now-playing").append().html("<h1 class='text-center'>You are playing as " + currentName + "</h1>");
      } else {
          $("#now-playing").append().html("<h1 class='text-center'>You are spectating as " + currentName + "</h1>");
      }

      whoAmI = currentName;


      if (whatAmI == "P1") {
          con2 = database.ref("/RPSp1").push(whoAmI);
      } else if (whatAmI == "P2") {
          con2 = database.ref("/RPSp2").push(whoAmI);
      } else {
          con2 = database.ref("/RPSspec").push(whoAmI);
      }
      con2.onDisconnect().remove();
      return con2;
  }

  // See if the recent user name will be a player or spectator
  function enterArena() {
      var currentName = "";
      var p = "";

      if ($("#player-name-entry").val().trim() == "") {
          return;
      }

      if (p1Name == "") {
          p1Name = $("#player-name-entry").val().trim();
          currentName = p1Name;
          p = "Player 1";
          $("#p1-name").html(p1Name);
          p1Access = true;
          whatAmI = "P1";
          database.ref("/RPSp1").set({
              p1NameKey: p1Name
          });
      } else if (p2Name == "") {
          p2Name = $("#player-name-entry").val().trim();
          currentName = p2Name;
          p = "Player 2";
          $("#p2-name").html(p2Name);
          p2Access = true;
          whatAmI = "P2";
          database.ref("/RPSp2").set({
              p2NameKey: p2Name
          });
      } else {
          specName = $("#player-name-entry").val().trim();
          currentName = specName;
          p = "Spectator";
          isSpectating = true;
          whatAmI = "Spectator";
      }
      $("#player-name-entry").val("");

      if (p1Name != "" && p2Name != "" && arenaOpen == false) {
          arenaOpen = true;
      }

      //Display what the user is
      $("#now-playing").empty();
      if (p1Access == true || p2Access == true) {
          $("#now-playing").append().html("<h1 class='text-center'>You are playing as " + currentName + "</h1>");
      } else {
          $("#now-playing").append().html("<h1 class='text-center'>You are spectating as " + currentName + "</h1>");
      }

      whoAmI = currentName;


      if (whatAmI == "P1") {
          con2 = database.ref("/RPSp1").push(whoAmI);
      } else if (whatAmI == "P2") {
          con2 = database.ref("/RPSp2").push(whoAmI);
      } else {
          con2 = database.ref("/RPSspec").push(whoAmI);
      }
      con2.onDisconnect().remove();
      return con2;

  }

  //Only allow P1 and P2 to play RPS.
  function pRPS() {
      if (whatAmI == "P1") {
          p1RPS();
      }
      if (whatAmI == "P2") {
          p2RPS();
      }
  }

  // Player 1's ability to play RPS
  function p1RPS() {
      if (p1Name == "" || p2Name == "" || p1Access == false) {
          return;
      }

      var p1Selected = $(event.target).attr("data");
      $("#p1-win").html("");
      $("#tie").html("");
      $("#p2-win").html("");



      p1Choice = p1Selected;
      $("#announcer").html("Waiting on " + p2Name + "!");

      database.ref("/RPSinfo").set({
          p1ChoiceKey: p1Choice,
          p2ChoiceKey: p2Choice,
          p1WinsKey: p1Wins,
          p1LossesKey: p1Losses,
          p2WinsKey: p2Wins,
          p2LossesKey: p2Losses,
          tiesKey: ties
      });

  }

  //Player 2's ability to play RPS
  function p2RPS() {
      if (p1Name == "" || p2Name == "" || p2Access == false) {
          return;
      }
      var p2Selected = $(event.target).attr("data");

      p2Choice = p2Selected;
      $("#announcer").html("Waiting on " + p1Name + "!");

      database.ref("/RPSinfo").set({
          p1ChoiceKey: p1Choice,
          p2ChoiceKey: p2Choice,
          p1WinsKey: p1Wins,
          p1LossesKey: p1Losses,
          p2WinsKey: p2Wins,
          p2LossesKey: p2Losses,
          tiesKey: ties
      });

  }


  // See who wins the RPS
  function result() {
      $("#announcer").html("Results are in!");

      if (p1Choice === "Rock") {
          $(".Rock").css({
              "border": "10px solid red",
              "padding": "5px"
          });
      }
      if (p1Choice === "Paper") {
          $(".Paper").css({
              "border": "10px solid red",
              "padding": "5px"
          });
      }
      if (p1Choice === "Scissors") {
          $(".Scissors").css({
              "border": "10px solid red",
              "padding": "5px"
          });
      }

      if (p2Choice === "Rock") {
          $(".Rock").css({
              "border": "10px solid blue",
              "padding": "5px"
          });
      }
      if (p2Choice === "Paper") {
          $(".Paper").css({
              "border": "10px solid blue",
              "padding": "5px"
          });
      }
      if (p2Choice === "Scissors") {
          $(".Scissors").css({
              "border": "10px solid blue",
              "padding": "5px"
          });
      }

      if (p2Choice === "Rock" && p1Choice === "Rock") {
          $(".Rock").css({
              "border": "10px solid yellow",
              "padding": "5px"
          });
      }
      if (p2Choice === "Paper" && p1Choice === "Paper") {
          $(".Paper").css({
              "border": "10px solid yellow",
              "padding": "5px"
          });
      }
      if (p2Choice === "Scissors" && p1Choice === "Scissors") {
          $(".Scissors").css({
              "border": "10px solid yellow",
              "padding": "5px"
          });
      }


      if ((p1Choice === "Rock") || (p1Choice === "Paper") || (p1Choice === "Scissors")) {

          if ((p1Choice === "Rock" && p2Choice === "Scissors") ||
              (p1Choice === "Scissors" && p2Choice === "Paper") ||
              (p1Choice === "Paper" && p2Choice === "Rock")) {
              $("#p1-win").html(p1Name + " wins!");
              $("#tie").html(p1Name + " picked " + p1Choice);
              $("#p2-win").html(p2Name + " picked " + p2Choice);
              p1Wins++;
              $("#p1-result").html(p1Name + " wins!");
              $("#p1-wins").html("Wins: " + p1Wins);
              p2Losses++;
              $("#p2-result").html(p2Name + " lost!");
              $("#p2-losses").html("Losses: " + p2Losses);
          } else if (p1Choice === p2Choice) {
              $("#tie").html(p1Name + " and " + p2Name + " tie!");
              $("#p2-win").html(p2Name + " picked " + p2Choice);
              $("#p1-win").html(p1Name + " picked " + p1Choice);
              ties++;
              $("#ties").html("Ties: " + ties);
              $("#p1-result").html(p1Name + " tied!");
              $("#p2-result").html(p2Name + " tied!");
          } else {
              $("#p2-win").html(p2Name + " wins!");
              $("#tie").html(p2Name + " picked " + p2Choice);
              $("#p1-win").html(p1Name + " picked " + p1Choice);
              p2Wins++;
              $("#p2-result").html(p2Name + " wins!");
              $("#p1-result").html(p1Name + " lost!");
              $("#p2-wins").html("Wins: " + p2Wins);
              p1Losses++;
              $("#p1-losses").html("Losses: " + p1Losses);
          }
      }

      p1Choice = "";
      p2Choice = "";
  }


  // If enter is pressed"keyCode === 13" in the $("#chat-message") input field, act as if it was submitted.
  var userInput = document.getElementById("chat-message");
  userInput.addEventListener("keyup", function (event) {
      if (event.keyCode === 13) {
          event.preventDefault();
          document.getElementById("chat-submit").click();
      }
  });

  //Chat box logic
  function chat() {
      if ($("#chat-message").val().trim() == "") {
          return;
      }
      if (whoAmI == "") {
          return;
      }

      chatMessage = "[ " + whoAmI + " ] - " + $("#chat-message").val().trim();
      $("#chat-message").val("");

      database.ref("/RPSchat").set({
          chatMessageKey: chatMessage,
          whoAmIKey: whoAmI
      });



      var currentChat = $("#chat-area").val().trim();

      if (currentChat == "") {
          $("#chat-area").html(chatMessage);
      } else {
          $("#chat-area").html(currentChat + "&#13" + chatMessage);
          $('#chat-area').scrollTop($('#chat-area')[0].scrollHeight);

      }

  }


  // Update users chat when RPSchat is updated.
  database.ref("RPSchat").on("value", function (snapshot) {
      chatMessage = snapshot.val().chatMessageKey;
      var whoSaidLast = snapshot.val().whoAmIKey;

      if (p1Access == true || p2Access == true || isSpectating == true) {
          if (whoSaidLast != whoAmI) {
              var currentChat = $("#chat-area").val().trim();

              if (currentChat == "") {
                  $("#chat-area").html(chatMessage);
              } else {
                  $("#chat-area").html(currentChat + "&#13" + chatMessage);
                  $('#chat-area').scrollTop($('#chat-area')[0].scrollHeight);

              }
          }
      }
  }, function (errorObject) {
      console.log("The read failed: " + errorObject.code);
  });

  //Update user when RPSp1 is updated.
  database.ref("RPSp1").on("value", function (snapshot) {
      var p1children = 1;
      var p2children = 1;

      database.ref("RPSp2").on("value", function (snapshot) {
          p2children = snapshot.numChildren();

      })

      if (snapshot.numChildren() == 2) {
          p1Name = snapshot.val().p1NameKey;
          p1children = 2;
      } else {
          p1Name = "";
          $("#announcer").html("Two players needed to play!")
          database.ref("/RPSinfo").set({
              p1ChoiceKey: "",
              p2ChoiceKey: "",
              p1WinsKey: 0,
              p1LossesKey: 0,
              p2WinsKey: 0,
              p2LossesKey: 0,
              tiesKey: 0
          });
          $("#p1-wins").html("Wins: 0");
          $("#p1-losses").html("Losses: 0");
          $("#ties").html("Ties: 0");
          $("#p2-wins").html("Wins: 0");
          $("#p2-losses").html("Losses: 0");
          $("#p1-win").html("");
          $("#p2-win").html("");
          $("#tie").html("");

          var currentChat = $("#chat-area").val().trim();
          if (currentChat == "" && p1children == 1) {
              $("#chat-area").html("[SERVER MESSAGE] ~Seat 1 Available~ Welcome to Rock Paper Scissors! Enter your name to take a seat and access the chat. If you are spectating and a seat becomes available, click 'Take a seat and play' to join!");
          } else if (snapshot.numChildren() == 1) {
              $("#chat-area").html(currentChat + "&#13" + "[SERVER MESSAGE] ~Seat 1 Available~ Welcome to Rock Paper Scissors! Enter your name to take a seat and access the chat. If you are spectating and a seat becomes available, click 'Take a seat and play' to join!");
              $('#chat-area').scrollTop($('#chat-area')[0].scrollHeight);

          }

      }

      var currentChat = $("#chat-area").val().trim();
      /* if (currentChat == "" && p2children != 2) { // this may not be needed

          $("#chat-area").html("[SERVER MESSAGE] ~Welcome Spectator~ Welcome to Rock Paper Scissors! Enter your name to take a seat and access the chat. If you are spectating and a seat becomes available, click 'Take a seat and play' to join!");
      } */


      if (p1Name != "") {
          $("#p1-name").html(p1Name);
      } else {
          $("#p1-name").html("Waiting for an opponent");
      }

      if (p2Name != "" && p1Name != "") {
          $("#announcer").html("Pick Rock Paper or Scissors!");

      }


      if (whatAmI == "Spectator" && (p2children == 1 || p1children == 1)) {
          $(".joinGame").html("<button id='takeP1' onclick='takeP1()'>Take a seat and play</button>");
      } else {
          $(".joinGame").empty();
      }

  }, function (errorObject) {
      console.log("The read failed: " + errorObject.code);
  });

  //Update user when RPSp2 is updated.
  database.ref("RPSp2").on("value", function (snapshot) {
      var p1children = 1;
      var p2children = 1;

      database.ref("RPSp1").on("value", function (snapshot) {
          p1children = snapshot.numChildren();
      })

      if (snapshot.numChildren() == 2) {
          p2Name = snapshot.val().p2NameKey;
          p2children = 2;

      } else {
          p2Name = "";
          $("#announcer").html("Two players needed to play!");
          database.ref("/RPSinfo").set({
              p1ChoiceKey: "",
              p2ChoiceKey: "",
              p1WinsKey: 0,
              p1LossesKey: 0,
              p2WinsKey: 0,
              p2LossesKey: 0,
              tiesKey: 0
          });
          $("#p1-wins").html("Wins: 0");
          $("#p1-losses").html("Losses: 0");
          $("#ties").html("Ties: 0");
          $("#p2-wins").html("Wins: 0");
          $("#p2-losses").html("Losses: 0");
          $("#p1-win").html("");
          $("#p2-win").html("");
          $("#tie").html("");

          var currentChat = $("#chat-area").val().trim();
          if (currentChat == "" && p2children == 1) {
              $("#chat-area").html("[SERVER MESSAGE] ~Seat 2 Available~ Welcome to Rock Paper Scissors! Enter your name to take a seat and access the chat. If you are spectating and a seat becomes available, click 'Take a seat and play' to join!");
          } else if (snapshot.numChildren() == 1) {

              $("#chat-area").html(currentChat + "&#13" + "[SERVER MESSAGE] ~Seat 2 Available~ Welcome to Rock Paper Scissors! Enter your name to take a seat and access the chat. If you are spectating and a seat becomes available, click 'Take a seat and play' to join!");
              $('#chat-area').scrollTop($('#chat-area')[0].scrollHeight);

          }

      }

      var currentChat = $("#chat-area").val().trim();
      if (currentChat == "" && p1children == 2) {
          $("#chat-area").html("[SERVER MESSAGE] ~Welcome Spectator~ Welcome to Rock Paper Scissors! Enter your name to take a seat and access the chat. If you are spectating and a seat becomes available, click 'Take a seat and play' to join!");
      }

      if (p2Name != "") {
          $("#p2-name").html(p2Name);
      } else {
          $("#p2-name").html("Waiting for an opponent");
      }

      if (p2Name != "" && p1Name != "") {
          $("#announcer").html("Pick Rock Paper or Scissors!")

      }

      if (whatAmI == "Spectator" && (p2children == 1 || p1children == 1)) {
          $(".joinGame").html("<button class='input-group-text' id='takeP2' onclick='takeP2()'>Take a seat and play</button>");
      } else {
          $(".joinGame").empty();
      }

  }, function (errorObject) {
      console.log("The read failed: " + errorObject.code);
  });


  //Update user when RPSinfo is updated.
  database.ref("RPSinfo").on("value", function (snapshot) {
      p1Choice = snapshot.val().p1ChoiceKey;
      p2Choice = snapshot.val().p2ChoiceKey;
      p1Wins = snapshot.val().p1WinsKey;
      p1Losses = snapshot.val().p1LossesKey;
      p2Wins = snapshot.val().p2WinsKey;
      p2Losses = snapshot.val().p2LossesKey;
      ties = snapshot.val().tiesKey;

      $(".Scissors").css({
          "border": "none"
      });
      $(".Rock").css({
          "border": "none"
      });
      $(".Paper").css({
          "border": "none"
      });

      if (p1Choice != "") {
          $("#announcer").html("Waiting on " + p2Name + "!");
          $("#p1-win").html("");
          $("#p2-win").html("");
          $("#tie").html("");
      }

      if (p2Choice != "") {
          $("#announcer").html("Waiting on " + p1Name + "!");
          $("#p1-win").html("");
          $("#p2-win").html("");
          $("#tie").html("");
      }

      $("#p1-wins").html("Wins: " + p1Wins);
      $("#p1-losses").html("Losses: " + p1Losses);
      $("#ties").html("Ties: " + ties);
      $("#p2-wins").html("Wins: " + p2Wins);
      $("#p2-losses").html("Losses: " + p2Losses);
      $("#p1-result").html("");
      $("#p2-result").html("");



      if (p2Choice != "" && p1Choice != "") {

          result();
      }

  }, function (errorObject) {
      console.log("The read failed: " + errorObject.code);
  });


  //=================================================================================
  // the code below is for counting how many users are viewing

  // connectionsRef references a specific location in our database.
  // All of our connections will be stored in this directory.
  var connectionsRef = database.ref("/connections");
  // '.info/connected' is a special location provided by Firebase that is updated every time
  // the client's connection state changes.
  // '.info/connected' is a boolean value, true if the client is connected and false if they are not.
  var connectedRef = database.ref(".info/connected");
  // When the client's connection state changes...

  // connectedRef.on("value", function (snap) {});
  database.ref("RPS1").on("value", function (snap) {

      // If they are connected..

      // Add user to the connections list.
      /* var con = connectionsRef.push("player1"); */

      // Remove user from the connection list when they disconnect.
      if (con2 != undefined) {
          con2.onDisconnect().remove();
      }

  });

  // When first loaded or when the connections list changes...
  connectionsRef.on("value", function (snapshot) {

      // Display the viewer count in the html.
      // The number of online users is the number of children in the connections list.
      $("#watchers").text(snapshot.numChildren()); //TODO: I need to fix this and reimplement
  });

  /*
  psudeocode
  1) object for game includes
  p1Name
  p2Name
  p1Choice
  p2Choice
  p1Wins
  p2Wins
  p1Losses
  p2Losses
  ties

  2) methods
  enterArena() {} // add player to game or tell spectate
  p1State {} //runs the screen printing for p1
  p2State {} //runs the screen printing for p2
  spectatorState() {} //runs the screen printing for spectators
  p1RPS() {} //player1's turn to select
  p2RPS() {} // player2's turn to select
  result() {} //who won? in this run the next method
  nextShowdown(){} // readies next showdown
  playerDC () {} // run this when player DC's
  keepP1(){} // logic for when p1 disconnects
  keepP2(){} // logic for when p2 disconnects
  */

  /*

  psudeocode for new board

  Title
  Name entry (swaps to player name / spectator name)
  * possibly allow a button to populate if whatAmI is a spec to take p1 or p2
  player board will be the rock paper scissors....
  user can click on their symbol announcer will be at the stop still
  when showdown happens display correct image for win and apply player 1 and player 2 borders for their corresponding selections
  when a selection is down highly what you selected.

  */