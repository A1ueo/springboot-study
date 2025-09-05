import { useNavigate } from 'react-router-dom'

export default function Login() {
  const nav = useNavigate()
  function login(e) {
    e.preventDefault()
    const form = new FormData(e.target)
    const all = Object.fromEntries(form.entries())

    fetch('http://localhost/api/member/login', {
      method: 'POST',
      body: form,
    })
      // .then((r) => r.json())
      .then((r) => {
        // console.log(r)
        // console.log(r.headers)
        const header = r.headers
        console.log(header.get('Access_token'))
        localStorage.setItem('access_token', header.get('Access_token'))
        sessionStorage.setItem('access_token', header.get('Access_token'))
        nav('/')
      })
      .catch((e) => console.log(e))
  }

  return (
    <>
      <h1>Login Page</h1>
      <div>
        <form onSubmit={login}>
          <input type='text' name='username' />
          <input type='password' name='password' />
          <button>Login</button>
        </form>
      </div>
    </>
  )
}
