// export default function StudyProps(props) {
// console.log(props)
// export default function StudyProps({ age, user }) {
//   console.log(age)
//   console.log(user)

// m =  {age:20, user:'123'}
// export default function StudyProps(props) {
//   console.log(props.m.age)
//   console.log(props.m.user)
export default function StudyProps({ m }) {
  console.log(m.age)
  console.log(m.name)

  return (
    <>
      <h1>Props Page</h1>
    </>
  )
}
