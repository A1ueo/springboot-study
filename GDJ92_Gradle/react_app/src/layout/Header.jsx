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
        <li>
          <Link to='/study/param/10/winter'>StudyParam2</Link>
        </li>
        <li>
          <Link to='/study/param' state={{ age: 10, user: "winter" }}>
            StudyParam3
          </Link>
        </li>

        <div>
          <li>
            <Link to='/member/login'>Login</Link>
          </li>
          <li>
            <Link to='/member/join'>Join</Link>
          </li>
        </div>
        <div>
          <li>
            <Link to='/member/logout'>Logout</Link>
          </li>
          <li>
            <Link to='/member/mypage'>Mypage</Link>
          </li>
        </div>
      </ul>
    </>
  )
}

export default Header
