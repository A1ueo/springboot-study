import { createContext, useEffect, useState } from "react"
import { Link } from "react-router-dom"

function List() {
  const [boards, setBoards] = useState([])
  const [page, setPage] = useState(0)

  // const name = "abc"
  // const products = [
  //     { title: 'Cabbage', id: 1 },
  //     { title: 'Garlic', id: 2 },
  //     { title: 'Apple', id: 3 },
  // ]

  // const lists = products.map(m => {
  //     return (<li key={m.id}>{m.title}</li>)
  // })

  useEffect(() => {
    fetch(`http://localhost/api/notice?page=${page}`)
      .then((r) => r.json())
      .then((r) => {
        console.log(r)
        const b = r.content.map((v) => <li key={v.boardNum}>{v.boardTitle}</li>)
        setBoards(b)
      })
  }, [page])

  // console.log(lists)
  function next() {
    setPage(page + 1)
  }

  return (
    <>
      <h1>List Page</h1>
      {/* <h1>{name}</h1> */}
      <ul>{boards}</ul>
      <div>
        <h3>Page: {page}</h3>
        <button onClick={next}>Next</button>
        <div>
          <Link to='/notice/add'>Notice Add</Link>
        </div>
      </div>
    </>
  )
}

export default List
