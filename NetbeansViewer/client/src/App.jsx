import React, { useState, useEffect, useRef } from 'react';
import './index.css';
import FileTree from './components/FileTree';
import CodeViewer from './components/CodeViewer';
import { Play, Bug, Save, Settings, Search, File, Menu } from 'lucide-react';

// Use relative path for Vercel, or localhost for dev if needed.
// Since we serve frontend separately in dev, we might need a proxy or keep using localhost:4000.
// But for Vercel build, it should be empty or /api.
const API_URL = import.meta.env.PROD ? '' : 'http://localhost:4000';
// Vercel doesn't support WS nicely in serverless. Disable or make optional.
const WS_URL = import.meta.env.PROD ? null : 'ws://localhost:4000';

function App() {
  const [fileTree, setFileTree] = useState(null);
  const [openFiles, setOpenFiles] = useState([]); // Array of { path, name, content, language }
  const [activeFilePath, setActiveFilePath] = useState(null);
  const [error, setError] = useState(null);
  const [isSidebarOpen, setIsSidebarOpen] = useState(window.innerWidth > 768);
  const ws = useRef(null);

  // Fetch Tree
  const fetchTree = async () => {
    setError(null);
    try {
      const res = await fetch(`${API_URL}/api/files`);
      if (!res.ok) throw new Error('Failed to fetch files');
      const data = await res.json();
      setFileTree(data);
    } catch (err) {
      console.error('Failed to load file tree', err);
      setError(err.message);
    }
  };

  useEffect(() => {
    fetchTree();

    // WebSocket Connection
    if (WS_URL) ws.current = new WebSocket(WS_URL);

    if (ws.current) ws.current.onmessage = (event) => {
      const { type, path } = JSON.parse(event.data);
      console.log('File system event:', type, path);

      // Simple strategy: Refetch tree on structural changes
      if (['add', 'unlink', 'addDir', 'unlinkDir'].includes(type)) {
        fetchTree();
      }

      // On file change, if open, reload content
      if (type === 'change') {
        // specific update logic could go here, or just let user re-click or auto-reload if active
        // For now, if active, reload it
        setOpenFiles(prev => prev.map(f => {
          if (f.path === path || path.endsWith(f.name)) { // simple matching, path from chokidar might be absolute
            // Trigger reload? simpler to just mark it dirty or fetch immediately if it's the active one
            // We'll fetch immediately if it's the active file
            if (activeFilePath === path || (activeFilePath && path.includes(activeFilePath))) { // loose match fix later
              fetchFileContent({ path: f.path, name: f.name }).then(newContent => {
                // update state
              });
            }
            return f; // TODO: handle background updates
          }
          return f;
        }));

        // Actually, let's just re-fetch the active file content if it changed
        if (activeFilePath && path.includes(activeFilePath)) { // simplified check
          // This is a bit tricky with paths. Chokidar returns full system path usually? 
          // We'll assume consistency for now or just wait for user interaction to avoid flickering
        }
      }
    };

    return () => {
      if (ws.current) ws.current.close();
    };
  }, []); // Re-fetching active file on change needs access to activeFilePath, so maybe inside another useEffect or ref

  // Helper to determine language
  const getLanguage = (filename) => {
    if (filename.endsWith('.java')) return 'java';
    if (filename.endsWith('.js') || filename.endsWith('.jsx')) return 'javascript';
    if (filename.endsWith('.html')) return 'html';
    if (filename.endsWith('.css')) return 'css';
    if (filename.endsWith('.json')) return 'json';
    return 'plaintext';
  };

  const fetchFileContent = async (fileNode) => {
    try {
      const res = await fetch(`${API_URL}/api/file?path=${encodeURIComponent(fileNode.path)}`);
      const data = await res.json();
      return data.content;
    } catch (e) {
      console.error(e);
      return "";
    }
  };

  const handleFileSelect = async (node) => {
    // Check if already open
    const existing = openFiles.find(f => f.path === node.path);
    if (existing) {
      setActiveFilePath(node.path);
      return;
    }

    const content = await fetchFileContent(node);
    const newFile = {
      path: node.path,
      name: node.name,
      content,
      language: getLanguage(node.name)
    };

    setOpenFiles([...openFiles, newFile]);
    setActiveFilePath(node.path);
    if (window.innerWidth <= 768) setIsSidebarOpen(false);
  };

  const handleCloseTab = (e, path) => {
    e.stopPropagation();
    const newOpen = openFiles.filter(f => f.path !== path);
    setOpenFiles(newOpen);
    if (activeFilePath === path) {
      setActiveFilePath(newOpen.length > 0 ? newOpen[newOpen.length - 1].path : null);
    }
  };

  const activeFile = openFiles.find(f => f.path === activeFilePath);

  return (
    <div className="nb-window">
      {/* Menubar */}
      <div className="nb-menubar">
        <div className="nb-menu-item">File</div>
        <div className="nb-menu-item">Edit</div>
        <div className="nb-menu-item">View</div>
        <div className="nb-menu-item">Navigate</div>
        <div className="nb-menu-item">Source</div>
        <div className="nb-menu-item">Refactor</div>
        <div className="nb-menu-item">Run</div>
        <div className="nb-menu-item">Debug</div>
        <div className="nb-menu-item">Profile</div>
        <div className="nb-menu-item">Team</div>
        <div className="nb-menu-item">Tools</div>
        <div className="nb-menu-item">Window</div>
        <div className="nb-menu-item">Help</div>
      </div>

      {/* Toolbar */}
      <div className="nb-toolbar">
        <div className="mobile-menu-btn" onClick={() => setIsSidebarOpen(!isSidebarOpen)}>
          <Menu size={16} color="#555" />
        </div>
        <File size={16} color="#555" />
        <Save size={16} color="#555" />
        <div style={{ width: 10 }}></div>
        <Play size={16} color="green" fill="green" />
        <Bug size={16} color="red" />
        <div style={{ width: 10 }}></div>
        <Search size={16} color="#555" />
      </div>

      <div className="nb-main">
        {/* Mobile Toggle Overlay */}
        {isSidebarOpen && window.innerWidth <= 768 && (
          <div className="sidebar-overlay" onClick={() => setIsSidebarOpen(false)}></div>
        )}

        {/* Sidebar */}
        <div className={`nb-sidebar ${isSidebarOpen ? 'open' : ''}`}>
          <div className="nb-sidebar-header">Projects</div>
          <div className="nb-tree">
            {fileTree ? (
              <FileTree node={fileTree} onSelect={handleFileSelect} />
            ) : (
              <div style={{ padding: 10 }}>
                {error ? <span style={{ color: 'red' }}>Error: {error}</span> : 'Loading...'}
              </div>
            )}
          </div>
          <div className="nb-sidebar-header" style={{ marginTop: 'auto' }}>Navigator</div>
          <div style={{ height: 150, padding: 5, overflow: 'auto', background: 'white' }}>
            {/* Mock members view */}
            {activeFile ? (
              <div style={{ fontSize: 11 }}>
                <div>Members</div>
                <div style={{ paddingLeft: 10 }}>main()</div>
              </div>
            ) : <div style={{ color: '#888', fontStyle: 'italic' }}>No file selected</div>}
          </div>
        </div>

        {/* Editor Area */}
        <div className="nb-editor-area">
          <div className="nb-tabs">
            {openFiles.map(file => (
              <div
                key={file.path}
                className={`nb-tab ${activeFilePath === file.path ? 'active' : ''}`}
                onClick={() => setActiveFilePath(file.path)}
              >
                <span style={{ fontSize: 11 }}>{file.name}</span>
                <span onClick={(e) => handleCloseTab(e, file.path)} style={{ marginLeft: 5, cursor: 'pointer', fontWeight: 'bold' }}>x</span>
              </div>
            ))}
          </div>

          <div className="nb-editor-content">
            {activeFile ? (
              <CodeViewer
                key={activeFile.path} // Force remount on file switch to ensure clean state or use same instance? key path ensures content update
                content={activeFile.content}
                path={activeFile.path}
                language={activeFile.language}
              />
            ) : (
              <div style={{ display: 'flex', justifyContent: 'center', alignItems: 'center', height: '100%', background: '#f0f0f0', color: '#888' }}>
                No content
              </div>
            )}
          </div>
        </div>
      </div>
    </div>
  );
}

export default App;
