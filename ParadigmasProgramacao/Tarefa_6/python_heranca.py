class A:
    x = 10 

class B:
    x = 20 

class C(B, A):
    def __init__(self):
        super().__init__()
    
    def print_x(self):
        print(self.x)

if __name__ == "__main__":
    c = C()
    c.print_x()