import { useLocation, useParams, useSearchParams } from "react-router-dom"

function StudyParam() {
  // 1. URLSearchParam -> 쿼리스트링
  const [param, setParam] = useSearchParams()
  console.log("num: " + param.get("num"))
  console.log("name: " + param.get("name"))

  // 2. useParams -> Restful 형식
  const { num, name } = useParams()
  console.log(num)
  console.log(name)

  // 또는
  const params = useParams()
  console.log(params.num)
  console.log(params.name)

  // 3. useLocation -> 쿼리스트링
  // const loc = useLocation()
  // const us = new URLSearchParams(loc.search)
  // console.log(us.get("num"))
  // console.log(us.get("name"))

  // 4. state
  const loc = useLocation()
  if (loc.state != null) {
    console.log(loc.state.age)
    console.log(loc.state.user)
  }

  return (
    <>
      <h1>Study Param</h1>
    </>
  )
}

export default StudyParam
