import { Link } from "react-router-dom"

function Header() {
  return (
    <>
      <h1>Header Line</h1>
      <ul>
        <li>
          <Link to='/'>Home</Link>
        </li>
        <li>
          <Link to='/notice/list'>Notice</Link>
        </li>
        <li>
          <Link to='/study/param?num=10&name=winter'>StudyParam</Link>
        </li>
      </ul>
    </>
  )
}

export default Header
