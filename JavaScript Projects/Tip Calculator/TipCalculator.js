// Calculate Tip
function calculateTip() {
  let billAmt = document.getElementById('billamt').value;
  let serviceQual = document.getElementById('serviceQual').value;
  let numOfPeople = document.getElementById('peopleamt').value;

  // validate input
  if (billAmt === "" || serviceQual == 0) {
    alert("Please enter values");
    return;
  }
  // Check to see if this input is empty or less than or equal to 1
  if (numOfPeople === "" || numOfPeople <= 1) {
    numOfPeople = 1;
    document.getElementById('each').style.dsiplay = "none";
  } else {
    document.getElementById('each').style.dsiplay = "block";
  }

  // Calculate Tip
  let total = (billAmt * serviceQual)/ numOfPeople;
  // round to two decimal places
  total = Math.round(total * 100)/ 100;
  // next lina allows us to always have two digits after decimal point
  total = total.toFixed(2);
  // Display the Tip
  document.getElementById('totalTip').style.display = "block";
  document.getElementById('tip').innerHTML = total;

}

// Hide the tip Amount on load
document.getElementById('totalTip').style.display = "none";
document.getElementById('each').style.display = "none";

// click to call function
document.getElementById("calculate").onclick = function() {
  calculateTip();
}
