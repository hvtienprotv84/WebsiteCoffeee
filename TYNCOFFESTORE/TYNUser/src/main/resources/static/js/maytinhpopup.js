document.getElementById("open_calculator").addEventListener("click", function() {
  document.getElementById("calculator_popup").style.display = "block";
});

document.getElementById("close_calculator").addEventListener("click", function() {
  document.getElementById("calculator_popup").style.display = "none";
});

var display = document.getElementById("calc_display");

function appendToDisplay(value) {
  display.value += value;
}

function calculate() {
  try {
    display.value = eval(display.value);
  } catch (error) {
    display.value = 'Error';
  }
}

function clearDisplay() {
  display.value = '';
}