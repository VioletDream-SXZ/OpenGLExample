cmake_minimum_required(VERSION 3.4.1)
    
# Enable C++11
set(CMAKE_CXX_STANDARD 11)

project(OpenGLExample)

set(OPENGL_LIB GL)

set(GLUTILS ./src/common/glUtils.cc)

add_executable(OpenGLExample
               ./src/main.cc
               ${GLUTILS})

target_link_libraries(OpenGLExample 
                      GL
                      GLU
                      glut)