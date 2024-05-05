function getCookie(cname) {
  let name = cname + "=";
  let decodedCookie = decodeURIComponent(document.cookie);
  let ca = decodedCookie.split(";");
  for (let i = 0; i < ca.length; i++) {
    let c = ca[i];
    while (c.charAt(0) == " ") {
      c = c.substring(1);
    }
    if (c.indexOf(name) == 0) {
      return c.substring(name.length, c.length);
    }
  }
  return "";
}

function deleteCookie(cookieName) {
  document.cookie =
    cookieName + "=; Path=/app; Expires=Thu, 01 Jan 1970 00:00:01 GMT;";
}

function showMessageAlert(message, state = "danger") {
  // Create an alert element
  var alertDiv = document.createElement("div");
  alertDiv.className = "alert alert-" + state + " alert-dismissible fade show";
  alertDiv.setAttribute("role", "alert");

  // Add alert content
  alertDiv.innerHTML = "<strong>Message!</strong> " + message;

  // Add close button
  var closeButton = document.createElement("button");
  closeButton.type = "button";
  closeButton.className = "close";
  closeButton.setAttribute("data-dismiss", "alert");
  closeButton.setAttribute("aria-label", "Close");
  closeButton.innerHTML = '<span aria-hidden="true">&times;</span>';
  alertDiv.appendChild(closeButton);

  // Insert alert before appending the body
  document.body.insertBefore(alertDiv, document.body.firstChild);
}

var token = getCookie("token");
var role = getCookie("role");

if (token && role) {
  console.log(token);
  console.log(role);
  if (role == "ADMIN") {
    document.getElementById("add").style.display = "block";
  }else{
    document.getElementById("myOrders").style.display = "block";
  }
  // Delete register button
  document.getElementById("registerNavItem").style.display = "none";

  // Change login button to log out button
  var loginButton = document.getElementById("loginButton");
  loginButton.textContent = "Logout";
  loginButton.onclick = logout; // Assigning the logout function to the onclick event

  // logout function deletes token and redirects to login page
  function logout() {
    // Code to delete token
    deleteCookie("token");
    deleteCookie("role");
  }
} else {
  showMessageAlert("you must login first");
}
