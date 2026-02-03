import React, { useState } from 'react';
import { Folder, FolderOpen, File, FileCode, Coffee, ChevronRight, ChevronDown } from 'lucide-react';

const FileIcon = ({ name }) => {
    if (name.endsWith('.java')) return <Coffee size={16} color="#e76f00" />;
    if (name.endsWith('.js') || name.endsWith('.jsx')) return <FileCode size={16} color="#f0db4f" />;
    if (name.endsWith('.html')) return <FileCode size={16} color="#e34c26" />;
    if (name.endsWith('.css')) return <FileCode size={16} color="#264de4" />;
    return <File size={16} color="#888" />;
};

const FileTree = ({ node, onSelect, level = 0 }) => {
    const [expanded, setExpanded] = useState(false);

    const handleClick = (e) => {
        e.stopPropagation();
        if (node.type === 'folder') {
            setExpanded(!expanded);
        } else {
            onSelect(node);
        }
    };

    return (
        <div style={{ paddingLeft: level === 0 ? 0 : 5 }}>
            <div
                className={`tree-node ${node.type === 'folder' ? 'folder' : 'file'}`}
                onClick={handleClick}
            >
                <span className="tree-indent" style={{ width: level * 16 }}></span>

                {node.type === 'folder' ? (
                    <span className="arrow">
                        {expanded ? <ChevronDown size={10} /> : <ChevronRight size={10} />}
                    </span>
                ) : <span className="arrow"></span>}

                <span className="tree-icon">
                    {node.type === 'folder' ? (
                        expanded ? <FolderOpen size={16} fill="#F8D775" color="#C9952E" /> : <Folder size={16} fill="#F8D775" color="#C9952E" />
                    ) : (
                        <FileIcon name={node.name} />
                    )}
                </span>

                <span className="tree-label">{node.name}</span>
            </div>

            {node.type === 'folder' && expanded && node.children && (
                <div className="tree-children">
                    {node.children
                        .sort((a, b) => {
                            if (a.type === b.type) return a.name.localeCompare(b.name);
                            return a.type === 'folder' ? -1 : 1;
                        })
                        .map((child) => (
                            <FileTree key={child.path} node={child} onSelect={onSelect} level={level + 1} />
                        ))}
                </div>
            )}
        </div>
    );
};

export default FileTree;
