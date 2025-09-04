import Header from "./layout/Header"
import AppRoutes from "./route/AppRoutes"
import StudyProps from "./study/StudyProps"

function App() {
  // const age = 10
  // let m = { age: 20, name: "123" }

  return (
    <>
      <Header />
      {/* <StudyProps age={age} name='user' obj={m} /> */}
      {/* <StudyProps m={m} /> */}
      <AppRoutes />
    </>
  )
}

export default App
