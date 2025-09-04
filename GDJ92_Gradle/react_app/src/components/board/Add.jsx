import { useRef, useState } from "react"
import { useNavigate } from "react-router-dom"

function Add() {
  const navigate = useNavigate()

  const [name, setName] = useState("test")
  const title = useRef()
  const writer = useRef()
  const contents = useRef()

  function get() {
    const params = {
      boardTitle: title.current.value,
      boardWriter: writer.current.value,
      boardContents: contents.current.value,
    }

    fetch("http://localhost/api/notice", {
      method: "POST",
      body: JSON.stringify(params),
      headers: {
        "Content-Type": "application/json",
      },
    })
      .then((r) => r.json())
      .then((r) => {
        if (r == true) {
          console.log(r)
          setName(JSON.stringify(r))
          navigate("../list")
        }
      })
  }

  const [add, setAdd] = useState({
    boardTitle: "",
    boardWriter: "",
    boardContents: "",
  })

  function inputChange(e) {
    setAdd((prevState) => ({
      ...prevState,
      [e.target.name]: e.target.value,
    }))
  }

  function get2() {
    console.log(add)
  }

  function sub(e) {
    e.preventDefault()
    const formdata = new FormData(e.target)

    fetch("http://localhost/api/notice/form", {
      method: "POST",
      body: formdata,
    })
      .then((r) => r.json())
      .then((r) => {
        if (r == true) {
          console.log(r)
          setName(JSON.stringify(r))
          navigate("../list")
        }
      })
  }

  const [files, setFiles] = useState([])
  const fileIdx = useRef(0)

  function delFile(e) {
    const idx = e.target.dataset.delIdx

    setFiles((prev) => prev.filter((f) => f.key !== idx))
  }

  function addFile() {
    if (files.length > 4) {
      alert("최대 5개까지 가능")
      return
    }
    const el = (
      <div key={fileIdx.current}>
        <input type='file' name='attaches' />
        <button data-del-idx={fileIdx.current} type='button' onClick={delFile}>
          X
        </button>
      </div>
    )

    fileIdx.current = fileIdx.current + 1

    const newFiles = [...files, el]
    setFiles(newFiles)
  }

  return (
    <>
      <h1>Add Page</h1>
      <h3>Input: {name}</h3>
      <form onSubmit={sub}>
        <input
          type='text'
          ref={title}
          name='boardTitle'
          onChange={inputChange}
        />
        <input
          type='text'
          ref={writer}
          name='boardWriter'
          onChange={inputChange}
        />
        <textarea
          ref={contents}
          name='boardContents'
          onChange={inputChange}
        ></textarea>
        <>{files}</>
        <div>
          <button type='button' onClick={addFile}>
            Add File
          </button>
        </div>
        <button>USEFORM</button>
      </form>
      <button onClick={get}>CLICK</button>
    </>
  )
}

export default Add
