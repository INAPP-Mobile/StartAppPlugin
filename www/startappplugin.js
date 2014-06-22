var StartAppPlugin = function() {
};

/**
 * Function to init 
 * @param successcallback
 * @param errorcallback
 * @param devId
 * @param appId
 */
StartAppPlugin.prototype.init = function(successCallback, errorCallback, devId, appId) {
    if (errorCallback == null) {
        errorCallback = function() {
        }
    }
    if ( typeof errorCallback != "function") {
        console.log("error callback parameter must be a function");
        return
    }
    if ( typeof successCallback != "function") {
        console.log("success callback parameter must be a function");
        return
    }

    cordova.exec(successCallback, // success callback function
    errorCallback, // error callback function
    'StartAppPlugin',
    'init', // with this action name
    [
    // and this array of custom arguments to create our entry
    {
        "devId" : devId,
        "appId" : appId
    }]);
};

/**
 * Function to Show
 * @param successcallback
 * @param errorcallback
 */
StartAppPlugin.prototype.show = function(successCallback, errorCallback) {
    if (errorCallback == null) {
        errorCallback = function() {
        }
    }
    if ( typeof errorCallback != "function") {
        console.log("error callback parameter must be a function");
        return
    }
    if ( typeof successCallback != "function") {
        console.log("success callback parameter must be a function");
        return
    }

    cordova.exec(successCallback, // success callback function
    errorCallback, // error callback function
    'StartAppPlugin', 'show', // with this action name
    [{// and this array of custom arguments to
        // create our entry

    }]);
};

//-------------------------------------------------------------------
module.exports = new StartAppPlugin();
