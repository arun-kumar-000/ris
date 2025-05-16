// Toggle profile dropdown
const profileImg = document.getElementById("profileImg");
const dropdown = document.getElementById("profileDropdown");

profileImg.addEventListener("click", () => {
    dropdown.style.display = dropdown.style.display === "block" ? "none" : "block";
});

window.addEventListener("click", function(event) {
    if (!profileImg.contains(event.target) && !dropdown.contains(event.target)) {
        dropdown.style.display = "none";
    }
});

// Sidebar toggle for mobile
const menuToggle = document.getElementById("menuToggle");
const sidebar = document.getElementById("sidebar");

menuToggle.addEventListener("click", () => {
    sidebar.classList.toggle("active");
});
