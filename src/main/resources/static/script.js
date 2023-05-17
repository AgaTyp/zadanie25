function checkOverdue() {
    let dueDateString = document.getElementById("dueDate").innerHTML;
    let dueDate = new Date(dueDateString);
    let today = new Date();
    if (dueDate < today) {
        dueDateString.style.color = "red";
    }
}
checkOverdue();

function categoryAdded() {
    let newCategory = document.getElementById("categoryName").innerHTML;
    alert("Dodana kategoria: " + newCategory)
}