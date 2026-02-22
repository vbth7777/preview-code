const express = require('express');
const cors = require('cors');
const fs = require('fs');
const path = require('path');
const http = require('http');

const app = express();
app.use(cors());
app.use(express.json());

// 1. Simulate Bundle Location
// In Vercel, we copied to project_data. locally it's in NetbeansViewer/project_data
const TARGET_DIR = path.resolve(__dirname, '../project_data');
console.log(`Target Dir: ${TARGET_DIR}`);

if (!fs.existsSync(TARGET_DIR)) {
    console.error(`ERROR: Target directory does not exist! Did the copy step work?`);
}

// 2. Serve Static Frontend (Simulate Vercel serving public/dist)
app.use(express.static(path.join(__dirname, '../client/dist')));

// 3. API Routes (Matching Vercel /api/...)
const getFileTree = (dir) => {
    if (!fs.existsSync(dir)) return { name: 'Error', type: 'file' };
    const stats = fs.statSync(dir);
    if (!stats.isDirectory()) {
        return { name: path.basename(dir), path: dir, type: 'file' };
    }
    const children = fs.readdirSync(dir).map(child => getFileTree(path.join(dir, child)));
    return { name: path.basename(dir), path: dir, type: 'folder', children };
};

app.get('/api/files', (req, res) => {
    try {
        const tree = getFileTree(TARGET_DIR);
        res.json(tree);
    } catch (err) {
        res.status(500).json({ error: err.message });
    }
});

app.get('/api/file', (req, res) => {
    const filePath = req.query.path;
    // Simple validation
    if (!filePath || !filePath.includes('project_data')) {
        return res.status(400).json({ error: 'Invalid file path' });
    }
    try {
        if (!fs.existsSync(filePath)) return res.status(404).json({ error: 'File not found' });
        const content = fs.readFileSync(filePath, 'utf-8');
        res.json({ content });
    } catch (err) {
        res.status(500).json({ error: 'Could not read file' });
    }
});

// Start
const PORT = 4000;
app.listen(PORT, () => {
    console.log(`\n--- Vercel Simulation Server ---`);
    console.log(`Serving app at http://localhost:${PORT}`);
    console.log(`Reading data from ${TARGET_DIR}`);
});
