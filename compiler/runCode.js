// compiler/runCode.js
const fs = require('fs');
const path = require('path');
const { exec } = require('child_process');
const { v4: uuid } = require('uuid');

const tempDir = path.join(__dirname, 'temp');
if (!fs.existsSync(tempDir)) fs.mkdirSync(tempDir);

const runCode = (language, code, input = '') => {
  return new Promise((resolve, reject) => {
    const filename = `${uuid()}.cpp`; // only cpp for now
    const filepath = path.join(tempDir, filename);
    const inputFilePath = filepath.replace('.cpp', '.input');

    fs.writeFileSync(filepath, code);
    fs.writeFileSync(inputFilePath, input);

    const executable = filepath.replace('.cpp', '.out');

    const compile = `g++ ${filepath} -o ${executable}`;
    const run = `${executable} < ${inputFilePath}`;

    exec(compile, (err, _, stderr) => {
      if (err) {
        // Clean up
        fs.unlinkSync(filepath);
        if (fs.existsSync(inputFilePath)) fs.unlinkSync(inputFilePath);
        return reject(new Error(`Compilation error:\n${stderr}`));
      }

      exec(run, (err, stdout, stderr) => {
        // Clean up files
        fs.unlinkSync(filepath);
        fs.unlinkSync(inputFilePath);
        if (fs.existsSync(executable)) fs.unlinkSync(executable);

        if (err) return reject(new Error(`Runtime error:\n${stderr}`));

        resolve(stdout.trim());
      });
    });
  });
};

module.exports = runCode;
