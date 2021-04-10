// JS with wait until DOM finished

// Function for waiting an object is available
// See https://stackoverflow.com/questions/8618464/how-to-wait-for-another-js-to-load-to-proceed-operation
function whenAvailable(name, callback) {
    var interval = 10; // ms
    window.setTimeout(function () {
        if (window[name]) {
            callback(window[name]);
        } else {
            whenAvailable(name, callback);
        }
    }, interval);
}

document.onreadystatechange = function () {
    if (document.readyState == "complete") {
        console.log('Document is ready, I can do something now. From JS withwait');

        // Wait until Calculator available
        whenAvailable("Calculator", function (t) {
            console.log('Result: ' + new Calculator('From index.html with JS wait').calculateSum([10, 20, 30]));
        });
    }
}

