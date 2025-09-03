import { useRef, useState } from "react"

function Add() {

    const [name, setName] = useState("test")
    const title = useRef()
    const writer = useRef()
    const contents = useRef()

    function get() {
        const params = {
            "boardTitle": title.current.value,
            "boardWriter": writer.current.value,
            "boardContents": contents.current.value
        }

        fetch('http://localhost/notice', {
            method: 'POST',
            body: JSON.stringify(params),
            headers: {
                'Content-Type': 'application/json'
            }
        })
        .then(r => r.json())
        .then(r => {
            if (r == true) {
                console.log(r)
                setName(JSON.stringify(r))
                
            }
        })
    }

    const [add, setAdd] = useState({
        boardTitle:"",
        boardWriter:"",
        boardContents:""
    })

    function inputChange(e) {
        setAdd((prevState) => ({
            ...prevState, 
            [e.target.name]: e.target.value
        }))
    }

    function get2() {
        console.log(add)
    }

    function sub(e) {
        e.preventDefault()
        const formdata = new FormData(e.target)

        fetch('http://localhost/notice/form', {
            method: 'POST',
            body: formdata
        })
        .then(r => r.json())
        .then(r => {
            if (r == true) {
                console.log(r)
                setName(JSON.stringify(r))
            }
        })
    }

    return (
        <>
            <h1>Add Page</h1>
            <h3>Input: {name}</h3>
            <form onSubmit={sub}>
                <input type="text" ref={title} name="boardTitle" onChange={inputChange} />
                <input type="text" ref={writer} name="boardWriter" onChange={inputChange} />
                <textarea ref={contents} name="boardContents" onChange={inputChange} ></textarea>
                <button>USEFORM</button>
            </form>
            <button onClick={get}>CLICK</button>
        </>
    )
}

export default Add
