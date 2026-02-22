const express = require('express');
const cors = require('cors');
const fs = require('fs');
const path = require('path');

const app = express();
app.use(cors());
app.use(express.json());

// Vercel serverless environment:
// Files are usually at the root or referenced relatively.
// We copied 'project_data/SchoolManagementSystem' to the root of the repo (or project_data folder).
// Use __dirname to be relative to the file location
const TARGET_DIR = path.join(__dirname, '..', 'project_data');

// Helper to get file tree
const getFileTree = (dir) => {
    if (!fs.existsSync(dir)) {
        // Return debug info
        return {
            name: 'Error',
            type: 'file',
            debug: {
                checkedPath: dir,
                cwd: process.cwd(),
                dirname: __dirname,
                lsRoot: fs.existsSync(path.join(__dirname, '..')) ? fs.readdirSync(path.join(__dirname, '..')) : 'parent not found'
            }
        };
    }

    const stats = fs.statSync(dir);
    if (!stats.isDirectory()) {
        return {
            name: path.basename(dir),
            path: dir, // We might need to obscure the real path or make it relative if we want to hide server structure, but for this viewer it's fine.
            type: 'file'
        };
    }

    const children = fs.readdirSync(dir).map(child => {
        const fullPath = path.join(dir, child);
        return getFileTree(fullPath);
    });

    return {
        name: path.basename(dir),
        path: dir,
        type: 'folder',
        children
    };
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

    // Security check: ensure path is within TARGET_DIR
    // On Vercel, paths might constitute the ephemeral FS, so strict checking is good practice but less critical for a read-only demo if sanitized.
    // However, the client sends the full path it received from /api/files.
    // We should trust paths coming from our own getFileTree but verification is better.
    if (!filePath || !filePath.startsWith(TARGET_DIR)) {
        return res.status(400).json({ error: 'Invalid file path' });
    }

    try {
        if (!fs.existsSync(filePath)) {
            return res.status(404).json({ error: 'File not found' });
        }
        const content = fs.readFileSync(filePath, 'utf-8');
        res.json({ content });
    } catch (err) {
        res.status(500).json({ error: 'Could not read file' });
    }
});

module.exports = app;
