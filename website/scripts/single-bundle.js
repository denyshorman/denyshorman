const fs = require('fs');

let indexHtml = fs.readFileSync('dist/main/index.html', {
  encoding: 'utf8',
  flag: 'r',
});

const runtimeJs = fs.readFileSync('dist/main/runtime.js', {
  encoding: 'utf8',
  flag: 'r',
});

const polyfillsJs = fs.readFileSync('dist/main/polyfills.js', {
  encoding: 'utf8',
  flag: 'r',
});

const mainJs = fs.readFileSync('dist/main/main.js', {
  encoding: 'utf8',
  flag: 'r',
});

const stylesCss = fs.readFileSync('dist/main/styles.css', {
  encoding: 'utf8',
  flag: 'r',
});

indexHtml = indexHtml.replace(
  `<script src=runtime.js type=module></script>`,
  `<script type=module>${runtimeJs}</script>`,
);

indexHtml = indexHtml.replace(
  `<script src=polyfills.js type=module></script>`,
  `<script type=module>${polyfillsJs}</script>`,
);

indexHtml = indexHtml.replace(`<script src=main.js type=module></script>`, `<script type=module>${mainJs}</script>`);

indexHtml = indexHtml
  .replace(`<link href=styles.css rel=stylesheet>`, `<style>${stylesCss}</style>`)
  .replace(/\r?\n|\r$/, '');

fs.writeFileSync('dist/main/index.html', indexHtml);

fs.unlinkSync('dist/main/runtime.js');
fs.unlinkSync('dist/main/polyfills.js');
fs.unlinkSync('dist/main/main.js');
fs.unlinkSync('dist/main/styles.css');
fs.unlinkSync('dist/main/3rdpartylicenses.txt');
