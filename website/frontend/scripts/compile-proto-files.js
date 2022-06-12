const path = require('path');
const fs = require('fs');
const process = require('child_process');
const os = require('os');

const cmdExt = os.platform() === 'win32' ? '.cmd' : '';
const protoFilesPath = path.normalize(path.join(__dirname, '..', '..', 'protocol'));
const nodeModulesBinPath = path.normalize(path.join(__dirname, '..', 'node_modules', '.bin'));
const compileOutputPath = path.normalize(path.join(__dirname, '..', 'projects', 'main', 'src', 'app', 'protocol'));

const protoFiles = fs
  .readdirSync(protoFilesPath)
  .filter(file => file.endsWith('.proto'))
  .map(file => path.join(protoFilesPath, file))
  .join(' ');

if (!fs.existsSync(compileOutputPath)) {
  fs.mkdirSync(compileOutputPath);
}

process.execFileSync(path.join(nodeModulesBinPath, `grpc_tools_node_protoc${cmdExt}`), [
  `--plugin=protoc-gen-ng=${path.join(nodeModulesBinPath, `protoc-gen-ng${cmdExt}`)}`,
  `--ng_out=${compileOutputPath}`,
  `--proto_path=${protoFilesPath}`,
  protoFiles,
]);
