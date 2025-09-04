import { Route, Routes } from "react-router-dom"
import List from "../components/board/List"
import Index from "../components/Index"
import Add from "../components/board/Add"
import StudyParam from "../study/StudyParam"

export default function AppRoutes() {
  return (
    <>
      <Routes>
        <Route path='/' element={<Index />} />
        <Route path='/notice/'>
          <Route path='list' element={<List />} />
          <Route path='add' element={<Add />} />
        </Route>
        <Route path='/study/param' element={<StudyParam />} />
      </Routes>
    </>
  )
}
