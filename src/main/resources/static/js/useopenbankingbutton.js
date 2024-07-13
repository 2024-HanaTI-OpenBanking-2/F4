
  function sendAPIRequest() {
  const apiUrl = 'https://api.example.com/data'; // 실제 API URL로 변경 필요
  fetch(apiUrl, {
  method: 'GET', // 'POST', 'PUT' 등의 메서드에 따라 변경 가능
  headers: {
  'Content-Type': 'application/json',
  // 필요한 경우 추가 헤더를 여기에 삽입
}
})
  .then(response => response.json())
  .then(data => {
  console.log('Success:', data);
  alert('오픈뱅킹 신청되었습니다: ' + JSON.stringify(data));
})
  .catch((error) => {
  console.error('Error:', error);
  alert('오픈뱅킹 이용하기를 실패하였습니다');
});
}

