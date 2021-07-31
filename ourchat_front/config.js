export default (function() {
  if (process.env.NODE_ENV === 'development') {
    console.log('开发环境');
    return 'http://motovill-test.club/';
    // return 'http://localhost:8000/'
  } else {
    console.log('生产环境');
        return '/';
  }
})();