#include <string.h>
#include <stdio.h>
#include "glUtils.h"

GLenum glUtils_errorCheck(void) {
    GLenum code;
    const GLubyte* string;
    code = glGetError();
    if (code != GL_NO_ERROR) {
        string = gluErrorString(code);
        fprintf(stderr, "OpenGl error:%s\n", string);
    }
    return code;
}