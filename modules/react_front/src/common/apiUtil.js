export const getApiUrl = () => {
    // 현재 도메인에서 포트만 바꿔서 API URL 생성
    const currentProtocol = window.location.protocol; // 예: 'http:'
    const currentHost = window.location.hostname; // 예: 'localhost'
    const targetPort = '8081'; // 변경할 포트 번호
    const apiUrl = `${currentProtocol}//${currentHost}:${targetPort}`;

    return apiUrl;
}


export const getUrlParam = (paramName) => {
    var queryString = window.location.search.substring(1);
    var regex = new RegExp('(^|&)' + paramName + '=([^&]*)(&|$)');
    var match = queryString.match(regex);

    if (match) {
        return decodeURIComponent(match[2]);
    }
    return null;
}