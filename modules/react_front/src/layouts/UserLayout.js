
const NoneLayout = ({ children }) => {
  return (
    <>
      <div style={{
        position: 'relative',
        width: '100%',
        height: '100%',
        background: 'gray',
      }}>
        <div style={{
          position: 'absolute',
          width: '400px',
          height: '700px',
          border: 'solid 1px',
          transform: 'translate(-50%, -50%)',
          top: '50%',
          left: '50%',
          background: 'white',
          display: 'flex',
          flexDirection: 'column',
          alignItems: 'center',
          justifyContent: 'center',
        }}>

          {children}

        </div>
      </div>
    </>
  )
}

export default NoneLayout;