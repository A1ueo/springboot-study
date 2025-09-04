export default function Login() {
  function login(e) {
    e.preventDefault()
    const form = new FormData(e.target)
    const all = Object.fromEntries(form.entries())

    fetch("http://localhost/api/member/login", {
      method: "POST",
      body: form,
    })
      .then((r) => r.json())
      .then((r) => {
        console.log(r)
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
