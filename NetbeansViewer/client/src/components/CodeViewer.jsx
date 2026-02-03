import React, { useRef, useEffect } from 'react';
import Editor from '@monaco-editor/react';

const CodeViewer = ({ content, path, language }) => {
    const editorRef = useRef(null);

    const triggerAlert = () => {
        // Check if already exists to avoid stacking
        if (document.querySelector('.custom-alert')) return;

        const alertBox = document.createElement('div');
        alertBox.className = 'custom-alert';
        alertBox.innerText = 'ĐM TỰ LÀM ĐÊ!!';
        document.body.appendChild(alertBox);

        setTimeout(() => {
            if (alertBox.parentElement) {
                alertBox.parentElement.removeChild(alertBox);
            }
        }, 1500);
    };

    const handleEditorDidMount = (editor, monaco) => {
        editorRef.current = editor;

        // KEY BINDINGS INTERCEPTION in Monaco
        // 2097 is Ctrl+C (KeyMod.CtrlCmd | KeyCode.KeyC)
        editor.addCommand(2097, () => {
            triggerAlert();
        });

        // Disable default context menu is handled by options
    };

    useEffect(() => {
        const handleCopy = (e) => {
            e.preventDefault();
            triggerAlert();
        };

        const handleKeyDown = (e) => {
            // Catch Ctrl+C on window level as backup
            if ((e.ctrlKey || e.metaKey) && (e.key === 'c' || e.key === 'C')) {
                handleCopy(e);
            }
        };

        // Attach to window to catch everything when this component is active
        window.addEventListener('copy', handleCopy);
        window.addEventListener('keydown', handleKeyDown);

        return () => {
            window.removeEventListener('copy', handleCopy);
            window.removeEventListener('keydown', handleKeyDown);
        };
    }, []);

    return (
        <div className="nb-editor-content" style={{ height: '100%', width: '100%' }}>
            <Editor
                height="100%"
                width="100%"
                language={language || 'java'}
                value={content}
                theme="light"
                options={{
                    readOnly: true,
                    domReadOnly: true,
                    minimap: { enabled: false },
                    lineNumbers: 'on',
                    scrollBeyondLastLine: false,
                    automaticLayout: true,
                    contextmenu: false,
                    fontFamily: "'Courier New', monospace",
                    fontSize: 13,
                }}
                onMount={handleEditorDidMount}
            />
        </div>
    );
};

export default CodeViewer;
