
window.onload = function () {
  document.getElementById("showLists").addEventListener("click", populateLists);
  // populateLists();
}

function populateLists() {
  let response = new XMLHttpRequest();
  response.onreadystatechange = function () {
    let bargh = response.responseText;
    console.log("xhttp status changed to " + response.readyState);
    if (response.readyState == 4 && response.status == 200) {
      // let groceryList = [];
      let groceryList = bargh.split('GroceryList');
      console.log(groceryList);
      populateTable(groceryList);
    }
  }
  response.open('GET', '/shop/grocery-lists');
  response.send();
}


function populateTable(groceryList) {
  let uList = document.getElementById("gLists");

  for (let i in groceryList) {

  }
}