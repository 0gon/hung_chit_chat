function getCookie(name) {
    var value = document.cookie.match('(^|;\\s*)' + name + '=([^;]*)');
    return value ? value[2] : null;
}

function getUrlParams() {
    var params = {};
    var queryString = window.location.search.substring(1);
    var regex = /([^&=]+)=([^&]*)/g;
    var match;

    while (match = regex.exec(queryString)) {
        params[decodeURIComponent(match[1])] = decodeURIComponent(match[2]);
    }

    return params;
}

function getUrlParam(paramName) {
    var queryString = window.location.search.substring(1);
    var regex = new RegExp('(^|&)' + paramName + '=([^&]*)(&|$)');
    var match = queryString.match(regex);

    if (match) {
        return decodeURIComponent(match[2]);
    }
    return null;
}

export { getCookie, getUrlParams, getUrlParam };
