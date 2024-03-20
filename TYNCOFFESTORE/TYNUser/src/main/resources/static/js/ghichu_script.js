function togglePopup() {
  var popupghichu = document.getElementById("popupghichu");
  popupghichu.style.display = (popupghichu.style.display === "block") ? "none" : "block";
}

function saveNote() {
  var noteInput = document.getElementById("noteInput");
  var noteList = document.getElementById("noteList");
  var noteText = noteInput.value;

  if (noteText.trim() !== "") {
    var noteLines = noteText.split("\n");
    noteLines.forEach(function(line) {
      if (line.trim() !== "") {
        var noteItem = document.createElement("li");
        noteItem.textContent = line;
        noteList.appendChild(noteItem);
      }
    });

    // Lưu ghi chú vào local storage
    localStorage.setItem("note", noteText);
  }

  noteInput.value = "";
}

// Kiểm tra và hiển thị ghi chú từ local storage khi trang đã tải xong
window.addEventListener("DOMContentLoaded", function() {
  var note = localStorage.getItem("note");
  if (note && note.trim() !== "") {
    var noteInput = document.getElementById("noteInput");
    noteInput.value = note;

    var noteList = document.getElementById("noteList");
    var noteLines = note.split("\n");
    noteLines.forEach(function(line) {
      if (line.trim() !== "") {
        var noteItem = document.createElement("li");
        noteItem.textContent = line;
        noteList.appendChild(noteItem);
      }
    });
  }
});