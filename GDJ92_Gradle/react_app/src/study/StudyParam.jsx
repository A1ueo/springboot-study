function StudyParam() {
  const url = new URL(window.location.href)
  const params = new URLSearchParams(url.search)

  // params.forEach((v, k) => {
  //   console.log(`${k} : ${v}`)
  // })

  console.log("num: " + params.get("num"))
  console.log("name: " + params.get("name"))

  return (
    <>
      <h1>Study Param</h1>
    </>
  )
}

export default StudyParam
