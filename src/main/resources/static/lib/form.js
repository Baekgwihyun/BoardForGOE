var updateButton = document.getElementById('updateDetails');
var favDialog = document.getElementById('favDialog');
var outputBox = document.getElementsByTagName('output')[0];
var selectEl = document.getElementsByTagName('select')[0];
var confirmBtn = document.getElementById('confirmBtn');

// “Update details” button opens the <dialog> modally
updateButton.addEventListener('click', function onOpen() {
    if (typeof favDialog.showModal === "function") {
        favDialog.showModal();
    }
});
// "Favorite animal" input sets the value of the submit button
selectEl.addEventListener('change', function onSelect(e) {
    confirmBtn.value = selectEl.value;
});
// "Confirm" button of form triggers "close" on dialog because of [method="dialog"]
favDialog.addEventListener('close', function onClose() {
    outputBox.value = favDialog.returnValue + " button clicked - " + (new Date()).toString();
    outputBox.value = favDialog.returnValue + " button clicked - " + (new Date()).toString();
});