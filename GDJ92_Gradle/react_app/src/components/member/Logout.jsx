import { useEffect } from 'react'
import { useNavigate } from 'react-router-dom'

export default function Logout() {
  sessionStorage.removeItem('access_token')
  localStorage.removeItem('access_token')
  const nav = useNavigate()
  useEffect(() => nav('/'))
}
