// =================
// KEYBOARD HANDLING
// =================

var keys = [];

function handleKeydown(evt) {
    // get rid of annoying space key moving down if there's a scroll
    if (evt.keyCode === keyCode(' ')) evt.preventDefault();
    
    keys[evt.keyCode] = true;
}

function handleKeyup(evt) {
    keys[evt.keyCode] = false;
}

// Inspects, and then clears, a key's state
//
// This allows a keypress to be "one-shot" e.g. for toggles
// ..until the auto-repeat kicks in, that is.
//
function eatKey(keyCode) {
    var isDown = keys[keyCode];
    keys[keyCode] = false;
    return isDown;
}

// A tiny little convenience function
function keyCode(keyChar) {
    return keyChar.charCodeAt(0);
}

window.addEventListener("keydown", handleKeydown);
window.addEventListener("keyup", handleKeyup);
